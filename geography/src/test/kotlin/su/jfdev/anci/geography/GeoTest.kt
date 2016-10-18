package su.jfdev.anci.geography

import io.kotlintest.properties.*
import io.kotlintest.specs.*
import su.jfdev.anci.geography.utils.*

/**
 * Jamefrus and his team on 27.06.2016.
 */
class GeoTest: FreeSpec() {
    init {
        "Geo.cube(x,y,z)" - {
            "should return IntRegion that has given point as #from and (from + size) as #to" {
                forAll(Gen.vec3i(), Gen.int()) { vec, size ->
                    val to = vec + (size - 1)
                    Geo.cube(vec, size) == (to from vec)
                }
            }
        }
    }
}