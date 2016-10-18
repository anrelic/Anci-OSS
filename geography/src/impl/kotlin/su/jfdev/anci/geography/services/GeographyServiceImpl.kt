package su.jfdev.anci.geography.services

import su.jfdev.anci.geography.*

object GeographyServiceImpl: GeographyService {
    override val vec3i: Vec3iStore = run {
        val length = System.getProperty("vec3i.cache.length", "64").toInt()
        when {
            length > 0 -> NonEmptyStore(length)
            else       -> EmptyStore
        }
    }
    override val intRegion: CoverageIterator<Vec3i, IntRegion>
        get() = IntRegionIteratorImpl
}

