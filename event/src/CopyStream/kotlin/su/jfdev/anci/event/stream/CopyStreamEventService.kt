package su.jfdev.anci.event.stream

import su.jfdev.anci.service.*
import java.util.concurrent.*

@Service
object CopyStreamEventService: StreamService() {
    override fun <E> newCollection() = CopyOnWriteArraySet<(E) -> Unit>()
}