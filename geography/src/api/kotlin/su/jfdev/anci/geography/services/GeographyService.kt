package su.jfdev.anci.geography.services

import su.jfdev.anci.geography.*
import su.jfdev.anci.service.*

@Service interface GeographyService {
    val vec3i: Vec3iStore
    val intRegion: CoverageIterator<Vec3i, IntRegion>

    companion object: GeographyService by service()
}

