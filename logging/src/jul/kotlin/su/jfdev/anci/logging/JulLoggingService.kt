package su.jfdev.anci.logging

object JulLoggingService: LoggingService {
    override fun adapter(name: String) = JulLogger(name)
}