package su.jfdev.anci.logging

import java.util.logging.*

class JulLogger(override val name: String): Logger {
    private val logger = java.util.logging.Logger.getLogger(name)
    private val LogLevel.level: Level get() = when (this) {
        LogLevel.TRACE -> Level.FINER
        LogLevel.DEBUG -> Level.FINE
        LogLevel.ERROR -> Level.SEVERE
        LogLevel.WARN  -> Level.WARNING
        LogLevel.INFO  -> Level.INFO
    }

    override fun contains(type: LogLevel) = logger.isLoggable(type.level)

    override fun print(type: LogLevel, text: String) = logger.log(type.level, text)
    override fun print(type: LogLevel, text: String, throwable: Throwable) = logger.log(type.level, text, throwable)
    override fun print(type: LogLevel, throwable: Throwable) = print(type, "", throwable)
}