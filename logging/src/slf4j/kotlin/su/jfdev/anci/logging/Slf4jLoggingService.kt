package su.jfdev.anci.logging

object Slf4jLoggingService: LoggingService {
    override fun logger(name: String) = Slf4jLogger(name)
}