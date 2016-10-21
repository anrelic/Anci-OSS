package su.jfdev.ekollections.readonly

class ROSet<out T>(override val delegate: Set<T>): ReadOnly(), Set<T> by delegate {
    override fun iterator(): Iterator<T> = delegate.iterator().readOnly()
}

