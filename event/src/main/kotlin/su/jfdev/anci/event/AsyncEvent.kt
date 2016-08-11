package su.jfdev.anci.event

import su.jfdev.anci.event.phase.*
import su.jfdev.anci.event.phase.Event.*
import kotlin.properties.*

/**
 * For hide handle, create extend Event<E> for API and AsyncEvent on internal.
 */
abstract class AsyncEvent<E: Enum<E>>(phase: E): Event<E>, Handle<E> {
    private val phases = phase.javaClass.enumConstants
    private var bus: Array<EventBus<Unit>>? = Array(size = phases.size) {
        EventService.createBus<Unit>(EventCompanion.MAIN_LOOP)
    }

    override var phase: E by Delegates.observable(phase) { prop, old, new ->
        bus(new)?.handle(Unit)
    }

    override fun on(phase: E, action: () -> Unit): Boolean {
        val bus = bus(phase) ?: return false
        bus.register { action() }
        return true
    }

    private fun bus(phase: E) = bus?.get(phase.ordinal)

    override fun done() {
        this.bus = null
    }
}

inline fun <P: Enum<P>, E: Event<P>> E.on(phase: P, crossinline action: (E) -> Unit) = on(phase) { action(this) }
inline fun <P: Enum<P>, E: Event<P>> E.with(phase: P, crossinline action: E.() -> Unit) = on(phase, action)
inline fun <P: Enum<P>> Handle<P>.during(begin: P, end: P, block: () -> Unit){
    phase = begin
    endWith(end, block)
}

inline fun <P: Enum<P>, R> Handle<P>.endWith(end: P, block: () -> R): R = block().apply {
    phase = end
}