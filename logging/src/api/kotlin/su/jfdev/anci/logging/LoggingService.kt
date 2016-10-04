package su.jfdev.anci.logging

import su.jfdev.anci.service.*

interface LoggingService {
    fun adapter(name: String): Logger

    companion object: LoggingService by service()
}