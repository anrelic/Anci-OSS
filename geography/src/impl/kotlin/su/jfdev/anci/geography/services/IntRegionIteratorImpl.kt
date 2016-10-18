@file:Suppress("NOTHING_TO_INLINE")

package su.jfdev.anci.geography.services

import su.jfdev.anci.geography.*
import java.util.*

object IntRegionIteratorImpl: CoverageIterator<Vec3i, IntRegion> {
    override fun iterator(coverage: IntRegion): Iterator<Vec3i> = when {
        coverage.isEmpty() -> Collections.emptyIterator()
        else               -> coverage.nonEmpty()
    }

    private inline fun IntRegion.nonEmpty() = object: Iterator<Vec3i> {
        private var x = from.x
        private var y = from.y
        private var z = from.z
        private var hasNext: Boolean = true

        inline fun hasX() = x < to.x
        inline fun hasY() = y < to.y
        inline fun hasZ() = z < to.z
        override fun hasNext() = hasNext
        override fun next(): Vec3i {
            if (!hasNext) throw NoSuchElementException()
            return Geo[x, y, z].apply {
                fold()
            }
        }

        inline fun fold() {
            if (hasZ()) z++
            else if (hasY()) {
                z = from.z
                y++
            } else if (hasX()) {
                z = from.z
                y = from.y
                x++
            } else hasNext = false
        }
    }
}