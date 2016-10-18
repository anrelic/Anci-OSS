package su.jfdev.anci.geography.services

import su.jfdev.anci.geography.*

class IntRegionImplIteratorSpec: CoverageIteratorSpec<Vec3i, IntRegion>(Geo[-555, -444, -323][44, 4, 4],
                                                                        IntRegionIteratorImpl)