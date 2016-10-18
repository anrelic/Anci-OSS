package su.jfdev.anci.species

import su.jfdev.anci.service.*

object GuavaSpeciesService: SpeciesService {
    override fun get(source: ServiceSource): SpeciesLoader = GuavaSpeciesLoader(source)
    override fun <T: Any> builder(): SpeciesBuilder<T> = GuavaSpecies.Builder()
}