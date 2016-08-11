package su.jfdev.anci.util

class CloseableReference<C: Any, O: Any>(open: O) {
    var closed: C? = null; private set
    var open: O? = open; private set

    fun closeBy(with: C) {
        open = null
        closed = with
    }
}