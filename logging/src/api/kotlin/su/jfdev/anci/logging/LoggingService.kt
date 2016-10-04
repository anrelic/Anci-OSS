package su.jfdev.anci.logging

import su.jfdev.anci.service.*

interface LoggingService {
    fun logger(name: String): Logger

    companion object: LoggingService by service()
}