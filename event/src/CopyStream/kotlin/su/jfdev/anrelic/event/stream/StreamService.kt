package su.jfdev.anrelic.event.stream

import su.jfdev.anrelic.event.*
import su.jfdev.anrelic.service.*

@Service
abstract class StreamService(): EventService {
    override fun createLoop(): EventLoop = StreamEventLoop
    override fun <E: Any> createBus(eventLoop: EventLoop): EventBus<E>
        = SimpleEventBus(eventLoop, newCollection())

    abstract fun <E> newCollection(): MutableCollection<(E) -> Unit>
}
