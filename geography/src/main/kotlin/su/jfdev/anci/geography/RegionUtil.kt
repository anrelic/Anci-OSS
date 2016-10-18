@file:Suppress("NOTHING_TO_INLINE")

package su.jfdev.anci.geography

/**
 * Jamefrus and his team on 27.06.2016.
 */

inline infix fun Vec3i.from(fromVec: Vec3i) = Geo[fromVec, this]

inline operator fun Vec3i.get(toX: Int, toY: Int, toZ: Int) = Geo[toX, toY, toZ] from this
fun IntRegion.expand(value: Int): IntRegion = Geo[from.move(-value, -value, -value), to.move(value, value, value)]
inline fun IntRegion.move(vec: Vec3i) = move(vec.x, vec.y, vec.z)
fun IntRegion.move(x: Int, y: Int, z: Int): IntRegion = Geo[from.move(x, y, z), to.move(x, y, z)]