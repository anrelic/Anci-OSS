package su.jfdev.anci.util.syntax

infix inline fun <T> T.finally(block: (T) -> Unit) = apply(block)

infix inline fun <T: Any> T?.`when null`(block: () -> Unit) = finally {
    if (it == null) block()
}

infix inline fun <T: Any> T?.`when not null`(block: (T) -> Unit) = finally {
    if (it != null) block(it)
}

infix inline fun <T: Boolean?> T.`when true`(block: (T) -> Unit): T = finally {
    if (it == true) block(it)
}

infix inline fun <T: Boolean?> T.`when false`(block: (T) -> Unit): T = finally {
    if (it == false) block(it)
}

inline fun <T> T.`when same`(inclusion: T, block: (T) -> Unit): T = finally {
    if (it === inclusion) block(it)
}

inline fun <T> T.`when other`(exclusion: T, block: (T) -> Unit): T = finally {
    if (it !== exclusion) block(it)
}

inline fun <T> T.`when equal`(inclusion: T, block: (T) -> Unit): T = finally {
    if (it == inclusion) block(it)
}

inline fun <T> T.`when not equal`(exclusion: T, block: (T) -> Unit): T = finally {
    if (it != exclusion) block(it)
}

inline fun <reified T> Any?.`when is`(action: (T) -> Unit) {
    if (this != null && this is T) return action(this)
}

inline fun <reified T> Any?.`when is not`(action: (T) -> Unit) {
    if (this != null && this is T) return action(this)
}

inline fun <reified T: Any> reify() = T::class.java