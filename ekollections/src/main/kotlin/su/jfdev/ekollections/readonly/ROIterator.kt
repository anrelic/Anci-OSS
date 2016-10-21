package su.jfdev.ekollections.readonly

class ROIterator<out T>(override val delegate: Iterator<T>): ReadOnly(), Iterator<T> by delegate