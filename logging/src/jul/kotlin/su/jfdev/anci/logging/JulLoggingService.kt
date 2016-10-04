package su.jfdev.anci.logging

object JulLoggingService: LoggingService {
    override fun logger(name: String) = JulLogger(name)
}