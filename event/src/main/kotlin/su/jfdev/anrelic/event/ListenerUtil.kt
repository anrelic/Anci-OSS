package su.jfdev.anrelic.event

import su.jfdev.anrelic.event.Prioritized.*

infix fun <T> Priority.listen(listener: (T) -> Unit): (T) -> Unit = object: Prioritized, (T) -> Unit by listener {
    override val priority: Priority get() = this@listen
}