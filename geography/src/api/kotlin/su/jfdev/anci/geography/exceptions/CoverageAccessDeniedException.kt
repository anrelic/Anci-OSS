@file:Suppress("CanBeParameter")

package su.jfdev.anci.geography.exceptions

import su.jfdev.anci.geography.*

class CoverageAccessDeniedException(
        val coverage: Collection<*>,
        val location: Vec3i): IllegalArgumentException("The $location is inaccessible in $coverage")