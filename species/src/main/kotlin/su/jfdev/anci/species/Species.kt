@file:JvmName("SubspeciesHelper")

package su.jfdev.anci.species

import su.jfdev.anci.*
import java.util.*
import kotlin.collections.Map.*
import kotlin.properties.*
import kotlin.reflect.*

/**
 * Transform [uuid] to UUID
 * @return [ReadOnlyProperty] that get value from [Species]
 * @see from
 */
infix fun <T: Any> Species<T>.by(uuid: String) = this by UUID.fromString(uuid)

/**
 * @return [ReadOnlyProperty] that get value from [Species]
 * @see from
 */
infix fun <T: Any> Species<T>.by(uuid: UUID) = object: ReadOnlyProperty<Any?, T> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): T = this@by from uuid
}

/**
 * Transform [uuid] to UUID and get value from result
 * @return value for [uuid]
 * @throws NoSuchElementException when receiver hasn't value
 * @throws IllegalArgumentException when [uuid] has illegal format
 */
infix fun <T: Any> Species<T>.from(uuid: String): T = this from UUID.fromString(uuid)

/**
 * When [uuid] hasn't value, throws [NoSuchElementException]
 * @return value for [uuid]
 * @throws NoSuchElementException
 */
infix fun <T: Any> Species<T>.from(uuid: UUID): T = get(uuid) ?: throw NoSuchElementException("species for $uuid")

/**
 * set [value] as value for [Identified.uuid]
 * @return [value]
 */
operator fun <T: Identified> SpeciesBuilder<T>.get(value: T) = get(value.uuid, value)

/**
 * set [value] as value for [uuid]
 * @return [value]
 */
operator fun <T: Any> SpeciesBuilder<T>.get(uuid: UUID, value: T): T = this(uuid) { value }

/**
 * set [value] result as value for [uuid]
 * @return [value] result
 */
inline operator fun <T: Any> SpeciesBuilder<T>.invoke(uuid: UUID, value: () -> T): T {
    val species = value()
    put(uuid, species)
    return species
}

@Suppress("UNCHECKED_CAST")
fun <T: Any> emptySpecies(): Species<T> = EMPTY_SPECIES as Species<T>

object EMPTY_SPECIES: Species<Any> {
    override fun iterator(): Iterator<Entry<UUID, Any>> = Collections.emptyIterator()
    override fun get(uuid: UUID): Any = iterator().next()
}