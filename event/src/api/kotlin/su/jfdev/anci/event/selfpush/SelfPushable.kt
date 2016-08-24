package su.jfdev.anci.event.selfpush

import su.jfdev.anci.event.*

interface SelfPushable<E: SelfPushable<E>> {
    val bus: EventBus<E>
}