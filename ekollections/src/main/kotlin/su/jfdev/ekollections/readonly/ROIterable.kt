package su.jfdev.ekollections.readonly

class ROIterable<out T>(override val delegate: Iterable<T>): ReadOnly(), Iterable<T> {
    override fun iterator(): Iterator<T> = delegate.iterator().readOnly()
}

