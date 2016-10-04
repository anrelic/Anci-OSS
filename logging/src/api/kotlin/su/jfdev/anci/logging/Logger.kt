package su.jfdev.anci.logging

interface Logger {
    val name: String
    operator fun contains(type: LogLevel): Boolean
    fun print(type: LogLevel, text: String)
    fun print(type: LogLevel, text: String, throwable: Throwable)
    fun print(type: LogLevel, throwable: Throwable)

    companion object {
        operator fun get(name: String) = LoggingService.logger(name)
    }
}