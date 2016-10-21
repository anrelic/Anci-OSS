package su.jfdev.ekollections.readonly

import java.util.*

class RORandomList<out T>(override val delegate: List<T>): ReadOnly(), RandomAccess, List<T> by delegate {
    override fun subList(fromIndex: Int, toIndex: Int): List<T> = delegate.subList(fromIndex, toIndex).readOnly()
    override fun listIterator(index: Int): ListIterator<T> = delegate.listIterator(index).readOnly()
    override fun listIterator(): ListIterator<T> = delegate.listIterator().readOnly()
    override fun iterator(): Iterator<T> = delegate.iterator().readOnly()
}

