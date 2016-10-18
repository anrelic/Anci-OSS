package su.jfdev.anci.geography

import io.kotlintest.specs.*
import su.jfdev.anci.geography.services.*

abstract class Vec3iStoreSpec(val store: Vec3iStore = GeographyService.vec3i): FreeSpec() {
    val cache: Collection<Vec3i> get() = store.cache

    init {
        "Vec3iStore should" - {
            "be loaded as serviced" {
                GeographyService.vec3i shouldBe store
            }
            "cache (return single instance every call)" - {
                "in $cache" {
                    forAll(cache) { vec ->
                        val (x, y, z) = vec
                        store[x, y, z] shouldBe store[x, y, z]
                    }
                }
            }
            "not cache (return different instances every call)" - {
                "in exclusions $cache" {
                    forAll { x: Int, y: Int, z: Int ->
                        val isCached = store[x, y, z] === store[x, y, z]
                        val shouldBeCached = Geo[x, y, z] in cache
                        shouldBeCached == isCached
                    }
                }
            }
        }
    }
}