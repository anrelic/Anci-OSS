package su.jfdev.anci.geography

import su.jfdev.anci.geography.services.*
import java.lang.Math.*

data class IntRegion internal constructor(val from: Vec3i, val to: Vec3i): Collection<Vec3i> {
    private inline fun sizeOf(getter: Vec3i.() -> Int)
            = max(to.getter() - from.getter() + 1, 0)

    override val size = sizeOf { x } * sizeOf { y } * sizeOf { z }

    override fun contains(element: Vec3i): Boolean {
        val (fromX, fromY, fromZ) = from
        val (toX, toY, toZ) = to
        val (x, y, z) = element
        return x in fromX..toX && y in fromY..toY && z in fromZ..toZ
    }

    operator fun contains(locations: Collection<Vec3i>) = locations.all { it in this }
    override fun containsAll(elements: Collection<Vec3i>) = contains(elements)

    override fun isEmpty() = size <= 0

    override fun iterator(): Iterator<Vec3i> = GeographyService.intRegion.iterator(this)
}