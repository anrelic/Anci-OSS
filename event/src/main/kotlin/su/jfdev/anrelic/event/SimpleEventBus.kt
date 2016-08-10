package su.jfdev.anrelic.event

class SimpleEventBus<T: Any>(override val loop: EventLoop,
                             private val subscribers: MutableCollection<(T) -> Unit>): EventBus<T> {

    override fun register(subscriber: (T) -> Unit) {
        subscribers += subscriber
    }

    override fun unregister(subscriber: (T) -> Unit) {
        subscribers -= subscriber
    }

    override fun handle(event: T) = loop.handle(subscribers, event)

    override fun sync(event: T) = loop.sync(subscribers, event)

}