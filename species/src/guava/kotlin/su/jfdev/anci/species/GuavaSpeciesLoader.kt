package su.jfdev.anci.species

import com.google.common.collect.ImmutableMap.*
import su.jfdev.anci.service.*
import su.jfdev.anci.service.ServiceLoader
import java.util.*

class GuavaSpeciesLoader(source: ServiceSource): SpeciesLoader {
    constructor(): this(ServiceSource(internal = setOf("species"), external = setOf("species")))

    val loader: ServiceLoader = ServiceLoader.with(source)
    override val source: ServiceSource get() = loader.source

    override fun <V: Any> get(type: Class<V>, name: String): Species<V> = builder<UUID, V>().run {
        collectSpecies(name, type)
        GuavaSpecies(build())
    }

    private fun <T: Any> Builder<UUID, T>.collectSpecies(name: String, type: Class<T>) {
        for (any in loader[name]) {
            if (any !is Species<*>) throw IllegalArgumentException("Species $any for $type")
            filterInstance(any, type)
        }
    }

    private fun <T: Any> Builder<UUID, T>.filterInstance(any: Species<*>, type: Class<T>) {
        for ((uuid, value) in any) {
            @Suppress("UNCHECKED_CAST")
            if (type.isInstance(value)) put(uuid, value as T)
        }
    }

}

