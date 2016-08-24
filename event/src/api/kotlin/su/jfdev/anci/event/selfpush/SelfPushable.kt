package su.jfdev.anci.event.selfpush

import su.jfdev.anci.event.*

interface SelfPushable<E: Any> {
    val bus: EventBus<E>
}