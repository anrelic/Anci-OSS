package su.jfdev.ekollections.readonly

class ROCollection<out T>(override val delegate: Collection<T>): ReadOnly(), Collection<T> by delegate {
    override fun iterator(): Iterator<T> = delegate.iterator().readOnly()
}