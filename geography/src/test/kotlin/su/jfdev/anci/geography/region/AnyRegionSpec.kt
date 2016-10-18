package su.jfdev.anci.geography.region

import su.jfdev.anci.geography.*

class AnyRegionSpec: RegionSpec(coverage = Geo[0, 0, 0][4, 4, 4],
                                included = listOf(Geo[1, 1, 1],
                                                  Geo[4, 4, 4],
                                                  Geo[0, 0, 0],
                                                  Geo[4, 0, 0],
                                                  Geo[0, 4, 0],
                                                  Geo[3, 3, 3]),
                                excluded = listOf(Geo[6, 6, 6],
                                                  Geo[5, 5, 5]),
                                size = 125) {
}