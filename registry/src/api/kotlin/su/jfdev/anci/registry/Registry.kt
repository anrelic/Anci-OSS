package su.jfdev.anci.registry

import su.jfdev.anci.*
import java.util.*

/**
 *  Similar to Map<UUID,V> container with unique values
 *
 * Any implementation:
 * 1) should be read-only
 * 2) should ensure that key equals [Identified.uuid] of value and vice-versa
 */
interface Registry<R: Identified>: Iterable<R> {
    /**
     * Count of registrations
     */
    val size: Int

    /**
     * Unmodifiable set of registrations
     */
    val values: Set<R>

    /**
     * Registration by [uuid]
     */
    operator fun get(uuid: UUID): R?

    /**
     * True if contains key [uuid]
     */
    operator fun contains(uuid: UUID): Boolean

    /**
     * True if contains [registration]
     */
    operator fun contains(registration: R) = registration in values

    /**
     * True - has any registrations
     */
    val isEmpty: Boolean get() = size == 0

    override fun iterator() = values.iterator()
}