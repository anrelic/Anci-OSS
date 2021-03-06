package su.jfdev.anci.event.stream

import kotlinx.support.jdk8.collections.*
import su.jfdev.anci.event.*
import su.jfdev.anci.event.Prioritized.*
import su.jfdev.anci.event.cancel.*

object StreamEventLoop: EventLoop {
    override fun <E: Any> handle(subscribers: Collection<(E) -> Unit>, event: E)
            = sync(subscribers, event)

    override fun <E: Any> sync(subscribers: Collection<(E) -> Unit>, event: E) {
        for (priority in Priority.values())
            subscribers.parallelStream()
                    .filter { it.priority == priority }
                    .forEach { if (event !is Cancelable || !event.cancelled) it(event) }
    }

    private val Any.priority: Priority get() = if(this is Prioritized) priority
    else Priority.DEFAULT
}