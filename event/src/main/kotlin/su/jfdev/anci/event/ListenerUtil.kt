package su.jfdev.anci.event

import su.jfdev.anci.event.Prioritized.*

infix inline fun <E> Priority.listen(crossinline listener: (E) -> Unit): (E) -> Unit = object: (E) -> Unit,
                                                                                               Prioritized {
    override val priority: Priority get() = this@listen
    override fun invoke(event: E) = listener(event)
}