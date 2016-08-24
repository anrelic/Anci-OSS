@file:Suppress("NOTHING_TO_INLINE")

package su.jfdev.anci.event.selfpush

import su.jfdev.anci.event.cancel.*

@JvmOverloads inline fun <E: SelfPushable<E>> E.push(sync: Boolean = true) = apply {
    if (sync) bus sync this
    else bus handle this
}


inline fun <E: SelfPushable<E>> E.pushing(using: (E) -> Unit) {
    push()
    whenContinue(using)
}
