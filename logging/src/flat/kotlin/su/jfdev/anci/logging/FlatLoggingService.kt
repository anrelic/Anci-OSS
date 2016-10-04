package su.jfdev.anci.logging

object FlatLoggingService: LoggingService {
    override fun adapter(name: String) = FlatLogger(name)
}