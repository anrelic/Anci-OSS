package su.jfdev.anci.event.phase

interface Event<E> where E: Enum<E> {
    val phase: E
    /**
     * @return false if event was done
     */
    fun on(phase: E, action: () -> Unit): Boolean

    interface Handle<E> where E: Enum<E> {
        var phase: E
        fun done()
        infix fun doneWith(phase: E) {
            this.phase = phase
            done()
        }
    }
}