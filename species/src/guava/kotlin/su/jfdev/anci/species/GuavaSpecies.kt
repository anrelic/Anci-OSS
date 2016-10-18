package su.jfdev.anci.species

import com.google.common.collect.*
import com.google.common.collect.ImmutableMap.*
import java.util.*
import kotlin.collections.Map.*

data class GuavaSpecies<T: Any>(val map: ImmutableMap<UUID, T>): Species<T> {
    override fun iterator(): Iterator<Entry<UUID, T>> = map.iterator()
    override fun get(uuid: UUID) = map[uuid]

    class Builder<T: Any>: SpeciesBuilder<T> {
        private var cache: Species<T>? = emptySpecies()
        private val map = Builder<UUID, T>()
        override fun put(uuid: UUID, item: T): SpeciesBuilder<T> = apply {
            map.put(uuid, item)
            cache = null
        }

        override fun build(): Species<T> = cache ?: GuavaSpecies(map.build()).apply { cache = this }
    }
}