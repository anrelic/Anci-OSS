package su.jfdev.anci.geography.services

import su.jfdev.anci.geography.*

interface Vec3iStore {
    val cache: Collection<Vec3i>
    operator fun get(x: Int, y: Int, z: Int): Vec3i
}