package su.jfdev.anci.logging

import org.slf4j.*

class Slf4jLogger(override val name: String): Logger {
    private val logger = LoggerFactory.getLogger(name)

    override fun contains(type: LogLevel) = when (type) {
        LogLevel.TRACE -> logger.isTraceEnabled
        LogLevel.DEBUG -> logger.isDebugEnabled
        LogLevel.ERROR -> logger.isErrorEnabled
        LogLevel.WARN  -> logger.isWarnEnabled
        LogLevel.INFO  -> logger.isInfoEnabled
    }

    override fun print(type: LogLevel, text: String) = when (type) {
        LogLevel.TRACE -> logger.trace(text)
        LogLevel.DEBUG -> logger.debug(text)
        LogLevel.ERROR -> logger.error(text)
        LogLevel.WARN  -> logger.warn(text)
        LogLevel.INFO  -> logger.info(text)
    }


    override fun print(type: LogLevel, throwable: Throwable) = print(type, "", throwable)

    override fun print(type: LogLevel, text: String, throwable: Throwable) = when (type) {
        LogLevel.TRACE -> logger.trace(text, throwable)
        LogLevel.DEBUG -> logger.debug(text, throwable)
        LogLevel.ERROR -> logger.error(text, throwable)
        LogLevel.WARN  -> logger.warn(text, throwable)
        LogLevel.INFO  -> logger.info(text, throwable)
    }

}