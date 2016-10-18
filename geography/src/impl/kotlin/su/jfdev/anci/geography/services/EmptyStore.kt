package su.jfdev.anci.geography.services

import su.jfdev.anci.geography.*

object EmptyStore: Vec3iStore {
    override val cache: Collection<Vec3i> get() = emptyList()
    override fun get(x: Int, y: Int, z: Int) = Vec3i.avoidCache(x, y, z)
}