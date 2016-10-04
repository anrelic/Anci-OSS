package su.jfdev.anci.logging

object Slf4jLoggingService: LoggingService {
    override fun adapter(name: String) = Slf4jLogger(name)
}