package su.jfdev.anci.logging

import su.jfdev.anci.logging.LogLevel.*
import su.jfdev.anci.util.syntax.*
import java.io.*
import java.util.*

class FlatLogger(override val name: String): Logger {

    override fun contains(type: LogLevel): Boolean = type <= level

    override fun print(type: LogLevel, text: String) = log(type, null, text)
    override fun print(type: LogLevel, text: String, throwable: Throwable) = log(type, throwable, text)
    override fun print(type: LogLevel, throwable: Throwable) = log(type, throwable, "")

    private fun log(level: LogLevel, ex: Throwable?, message: String) {
        if (contains(level)) {
            val date = Date()
            val type = level.name.toUpperCase()
            val stackTrace = when (ex) {
                null -> ""
                else -> ex.stackTrace()
            }
            println("$date $type: [$name] $message\n$stackTrace")
        }
    }

    private fun Throwable.stackTrace(): CharSequence = StringWriter().finally {
        printStackTrace(PrintWriter(it))
    }.buffer.trim()


    companion object Config {
        private val level: LogLevel

        init {
            val given = System.getProperty("logging.level", "info")
            level = LogLevel.values().find {
                it.name.equals(given, ignoreCase = true)
            } ?: INFO
        }
    }

}