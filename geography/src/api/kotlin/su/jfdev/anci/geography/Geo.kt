package su.jfdev.anci.geography

import su.jfdev.anci.geography.services.*

/**
 * Jamefrus and his team on 26.06.2016.
 */
object Geo {
    @JvmStatic operator fun get(x: Int, y: Int, z: Int) = GeographyService.vec3i[x, y, z]
    @JvmStatic operator fun get(from: Vec3i, to: Vec3i) = IntRegion(from, to)
    @JvmStatic fun cube(at: Vec3i, size: Int) = Geo[at, at + (size - 1)]
}