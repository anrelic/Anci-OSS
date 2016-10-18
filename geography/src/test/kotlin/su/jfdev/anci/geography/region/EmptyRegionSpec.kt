package su.jfdev.anci.geography.region

import su.jfdev.anci.geography.*

class EmptyRegionSpec: RegionSpec(coverage = Geo[0, 0, 0][-1, -1, -1],
                                  included = emptyList(),
                                  excluded = listOf(Geo[0, 0, 0],
                                                    Geo[-1, -1, -1],
                                                    Geo[1, 1, 1]),
                                  size = 0)