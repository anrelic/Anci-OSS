package su.jfdev.anrelic.event.cancel

interface Cancelable {
    val cancelled: Boolean
    /**
     * @return false if already cancelled or too late
     */
    fun cancel(): Boolean

    /**
     * You can implement only internal implementation (it define cancel())
     */
    interface Mutable: Cancelable {
        override var cancelled: Boolean
        override fun cancel(): Boolean {
            if (cancelled) return false
            cancelled = true
            return true
        }
    }
}