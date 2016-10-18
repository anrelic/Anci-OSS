package su.jfdev.anci.geography.services

import su.jfdev.anci.geography.*

class NonEmptyStore(length: Int): Vec3iStore {
    private val LAST = length - 1
    override val cache: Collection<Vec3i> by lazy {
        Geo[0, 0, 0][LAST, LAST, LAST]
    }

    private val cached: Array<Array<Array<Vec3i>>> by lazy {
        Array(length) { x ->
            Array(length) { y ->
                Array(length) { z ->
                    Vec3i.avoidCache(x, y, z)
                }
            }
        }
    }

    override fun get(x: Int, y: Int, z: Int) = if (x in 0..LAST && y in 0..LAST && z in 0..LAST) cached[x][y][z]
    else Vec3i.avoidCache(x, y, z)
}