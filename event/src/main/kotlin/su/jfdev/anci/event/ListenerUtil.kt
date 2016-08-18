package su.jfdev.anci.event

import su.jfdev.anci.event.Prioritized.*
import su.jfdev.anci.event.phase.*

infix inline fun <E> Priority.listen(crossinline listener: (E) -> Unit): (E) -> Unit = object: (E) -> Unit,
                                                                                               Prioritized {
    override val priority: Priority get() = this@listen
    override fun invoke(event: E) = listener(event)
}


inline fun <P: Enum<P>, E: Event<P>> E.with(phase: P, crossinline action: E.() -> Unit) = on(phase){ action() }

inline fun <P: Enum<P>> Event<P>.on(phase: P, action: () -> Unit){
    if(this.phase == phase) action()
}