@file:Suppress("NOTHING_TO_INLINE")

package su.jfdev.anci.geography

import su.jfdev.anci.geography.exceptions.*

inline fun <R> Collection<Vec3i>.checkedAccess(location: Vec3i, using: (Vec3i) -> R): R {
    checkAccess(location)
    return using(location)
}

fun Collection<Vec3i>.checkAccess(location: Vec3i) {
    if (location !in this) throw CoverageAccessDeniedException(this, location)
}