package su.jfdev.ekollections.readonly

class ROSequence<out T>(override val delegate: Sequence<T>): ReadOnly(), Sequence<T> {
    override fun iterator(): Iterator<T> = delegate.iterator().readOnly()
}