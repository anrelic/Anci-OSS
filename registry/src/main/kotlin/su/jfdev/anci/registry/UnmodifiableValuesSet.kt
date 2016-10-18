package su.jfdev.anci.registry

class UnmodifiableValuesSet<R>(val collection: Collection<R>): Set<R> {
    private val sequence = collection.asSequence().distinct()
    override val size: Int get() = sequence.count()

    override fun contains(element: R) = element in collection
    override fun containsAll(elements: Collection<R>) = collection.containsAll(elements)
    override fun isEmpty() = collection.isEmpty()
    override fun iterator() = sequence.iterator()
}