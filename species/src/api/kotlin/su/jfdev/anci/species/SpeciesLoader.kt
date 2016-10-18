package su.jfdev.anci.species

import su.jfdev.anci.service.*

interface SpeciesLoader {
    val source: ServiceSource

    operator fun <V: Any> get(type: Class<V>, name: String): Species<V>
    operator fun <V: Any> get(type: Class<V>): Species<V> = get(type, type.canonicalName)

    companion object: SpeciesLoader by SpeciesService[ServiceSource["species"]]
}

