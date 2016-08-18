package su.jfdev.anci.event.cancel

interface Cancelable {
    val cancelled: Boolean
    /**
     * @return false if already cancelled or too late
     */
    fun cancel(): Boolean
}