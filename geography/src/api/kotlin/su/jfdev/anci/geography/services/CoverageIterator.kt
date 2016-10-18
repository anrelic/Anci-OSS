package su.jfdev.anci.geography.services

interface CoverageIterator<out I, in T: Collection<I>> {
    fun iterator(coverage: T): Iterator<I>
}