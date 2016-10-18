package su.jfdev.anci.geography.region

import su.jfdev.anci.geography.*

/**
 * Jamefrus and his team on 26.06.2016.
 */
abstract class RegionSpec(included: Collection<Vec3i>,
                          excluded: Collection<Vec3i>,
                          coverage: Collection<Vec3i>,
                          size: Int): CoverageSpec<Vec3i>(included, excluded, coverage) {
    init {
        "should have given size" {
            coverage.size shouldBe size
        }
    }
}

