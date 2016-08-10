package su.jfdev.anrelic.event

@Suppress("UNUSED_PARAMETER")
abstract class EventCompanion<E: Any>(loop: EventLoop) {
    val bus: EventBus<E> = EventService.createBus<E>(loop)

    constructor(): this(MAIN_LOOP)

    constructor(parent: EventCompanion<in E>): this(parent.bus.loop, parent)

    constructor(loop: EventLoop, parent: EventCompanion<in E>): this(loop){
        bus register { parent push it }
    }

    infix fun push(event: E) = bus sync event
    infix fun register(subscriber: (E) -> Unit) = bus register subscriber

    companion object {
        val MAIN_LOOP: EventLoop by lazy { EventService.createLoop() }
    }
}