package su.jfdev.anci.registry

import su.jfdev.anci.rules.*
import java.util.*

/**
 * Unmodifiable checked map container
 */
interface Registry<R: Unique>: Iterable<R> {
    /**
     * Class of [R]
     */
    val type: Class<R>

    /**
     * Count of registrations
     */
    val size: Int

    /**
     * Unmodifiable set of registrations
     */
    val values: Set<R>

    /**
     * True if contains key [uuid]
     */
    operator fun contains(uuid: UUID): Boolean

    /**
     * Registration by [uuid]
     */
    operator fun get(uuid: UUID): R?

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