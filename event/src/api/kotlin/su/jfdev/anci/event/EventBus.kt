package su.jfdev.anci.event

/**
 * Jamefrus and his team on 13.06.2016.
 */
interface EventBus<E: Any> {
    val loop: EventLoop
    infix fun register(subscriber: (E) -> Unit)
    infix fun unregister(subscriber: (E) -> Unit)
    infix fun handle(event: E)
    infix fun sync(event: E)
}