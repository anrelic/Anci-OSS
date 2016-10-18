package su.jfdev.anci.species

import java.util.*
import kotlin.collections.Map.*

open class Subspecies<T: Any>(protected val species: SpeciesBuilder<T> = SpeciesService.builder()): Species<T> {
    override fun get(uuid: UUID) = species.build()[uuid]
    override fun iterator(): Iterator<Entry<UUID, T>> = species.build().iterator()
}