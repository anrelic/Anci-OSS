package su.jfdev.anrelic.event

/**
 * Jamefrus and his team on 14.06.2016.
 */
object DummyBus: EventBus<Any> {
    override fun unregister(subscriber: (Any) -> Unit) = Unit

    override fun handle(event: Any) = Unit
    override fun sync(event: Any) = Unit

    override val loop: EventLoop = object: EventLoop {
        override fun <E: Any> handle(subscribers: Collection<(E) -> Unit>, event: E) = Unit
        override fun <E: Any> sync(subscribers: Collection<(E) -> Unit>, event: E) = Unit
    }


    override fun register(subscriber: (Any) -> Unit) = Unit
}