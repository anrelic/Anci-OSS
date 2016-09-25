@file:Suppress("unused")

package su.jfdev.test.history

import su.jfdev.anci.util.syntax.*
import java.util.concurrent.*

class EventHistory<T>(register: ((T) -> Unit) -> Unit, private val delegate: MutableCollection<T>): Collection<T> by delegate {
    constructor(register: ((T) -> Unit) -> Unit): this(register, delegate = CopyOnWriteArrayList())
    var active = true

    init {
        register {
            if (active) delegate += it
        }
    }

    inline infix fun <R> temporary(using: EventCatcher<T>.() -> R) = then(using) finally {
        active = false
    }

    inline infix fun <R> then(using: EventCatcher<T>.() -> R) = EventCatcher(this).run(using)

    companion object
}