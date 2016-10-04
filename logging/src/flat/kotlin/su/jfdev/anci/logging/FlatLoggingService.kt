package su.jfdev.anci.logging

object FlatLoggingService: LoggingService {
    override fun logger(name: String) = FlatLogger(name)
}