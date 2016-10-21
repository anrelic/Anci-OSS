package su.jfdev.ekollections.readonly

class ROListIterator<out T>(override val delegate: ListIterator<T>): ReadOnly(), ListIterator<T> by delegate