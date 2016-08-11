package su.jfdev.anci.event.stream

import su.jfdev.anci.event.*
import su.jfdev.anci.service.*

@Service
abstract class StreamService(): EventService {
    override fun createLoop(): EventLoop = StreamEventLoop
    override fun <E: Any> createBus(eventLoop: EventLoop): EventBus<E>
        = SimpleEventBus(eventLoop, newCollection())

    abstract fun <E> newCollection(): MutableCollection<(E) -> Unit>
}
