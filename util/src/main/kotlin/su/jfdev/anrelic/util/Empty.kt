package su.jfdev.anrelic.util

import java.io.*

object EmptyCloseable: Closeable {
    override fun close() = Unit
}