package su.jfdev.anci.logging

import kotlin.reflect.*

/**
 * I recommend to use domain-likely loggers:
 * as example: "network.message"
 */
interface Logger {
    val name: String
    operator fun contains(type: LogLevel): Boolean
    fun print(type: LogLevel, text: String)
    fun print(type: LogLevel, text: String, throwable: Throwable)
    fun print(type: LogLevel, throwable: Throwable)

    companion object {
        operator fun get(name: String) = LoggingService.logger(name)
        operator fun get(clazz: Class<*>) = Logger[clazz.name]

        /**
         * Inline to avoid KClass creating
         */
        @Suppress("NOTHING_TO_INLINE")
        inline operator fun get(clazz: KClass<*>) = Logger[clazz.java]

        inline operator fun <reified C: Any> invoke() = Logger[C::class.java]
    }
}