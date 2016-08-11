package su.jfdev.anci.util

import java.io.*

object EmptyCloseable: Closeable {
    override fun close() = Unit
}