package su.jfdev.anci.species

import java.util.*

interface SpeciesBuilder<T: Any> {
    fun put(uuid: UUID, item: T): SpeciesBuilder<T>
    fun build(): Species<T>
}