package su.jfdev.anci.event

import su.jfdev.anci.service.*

@Service interface EventService {
    fun createLoop(): EventLoop
    fun <E: Any> createBus(eventLoop: EventLoop): EventBus<E>

    companion object: EventService by service()
}