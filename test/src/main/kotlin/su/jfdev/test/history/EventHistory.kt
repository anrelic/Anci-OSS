@file:Suppress("unused")

package su.jfdev.test.history

import su.jfdev.anci.util.syntax.*
import java.util.concurrent.*

class EventHistory<T>(val register: ((T) -> Unit) -> Unit) {
    var active = true
    private val events = CopyOnWriteArrayList<T>().apply {
        register {
            if (active) add(it)
        }
    }

    inline infix fun <R> temporary(using: EventCatcher<T>.() -> R) = then(using) finally {
        active = false
    }

    inline infix fun <R> then(using: EventCatcher<T>.() -> R) = EventCatcher(this).run(using)
    operator fun iterator(): Iterator<T> = events.iterator()

    companion object {
    }
}