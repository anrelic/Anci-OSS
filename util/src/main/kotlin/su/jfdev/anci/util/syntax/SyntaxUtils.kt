package su.jfdev.anci.util.syntax

infix inline fun <T> T.finally(block: (T) -> Unit) = apply(block)

infix inline fun <T: Any> T?.`when null`(block: () -> Unit): T? = finally {
    if (it == null) block()
}

infix inline fun <T: Any> T?.`when not null`(block: (T) -> Unit): T? = finally {
    if (it != null) block(it)
}