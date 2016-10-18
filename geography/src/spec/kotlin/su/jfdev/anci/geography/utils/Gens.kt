@file:Suppress("unused")

package su.jfdev.anci.geography.utils

import io.kotlintest.properties.*
import su.jfdev.anci.geography.*

fun Gen.Companion.vec3i() = Gen.create {
    Geo.get(
            x = Gen.int().generate(),
            y = Gen.int().generate(),
            z = Gen.int().generate()
           )
}