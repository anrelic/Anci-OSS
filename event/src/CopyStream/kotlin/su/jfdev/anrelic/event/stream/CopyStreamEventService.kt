package su.jfdev.anrelic.event.stream

import su.jfdev.anrelic.service.*
import java.util.concurrent.*

@Service
object CopyStreamEventService: StreamService() {
    override fun <E> newCollection() = CopyOnWriteArraySet<(E) -> Unit>()
}