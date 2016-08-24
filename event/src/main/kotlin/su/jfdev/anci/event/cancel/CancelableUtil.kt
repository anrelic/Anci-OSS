package su.jfdev.anci.event.cancel

inline fun <E: Any> E.whenContinue(using: (E) -> Unit) {
    if (!cancelled) using(this)
}

val <E: Any> E.cancelled: Boolean get() = this is Cancelable && cancelled
