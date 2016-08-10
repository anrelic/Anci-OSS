package su.jfdev.anrelic.event

import su.jfdev.anrelic.service.*

@Service interface EventService {
    fun createLoop(): EventLoop
    fun <E: Any> createBus(eventLoop: EventLoop): EventBus<E>

    companion object: EventService by service()
}