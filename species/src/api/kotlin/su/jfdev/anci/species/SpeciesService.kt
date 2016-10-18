package su.jfdev.anci.species

import su.jfdev.anci.service.*

@Service interface SpeciesService {
    operator fun get(source: ServiceSource): SpeciesLoader
    fun <T: Any> builder(): SpeciesBuilder<T>


    companion object: SpeciesService by service()
}