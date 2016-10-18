package su.jfdev.anci.species

import java.util.*
import kotlin.collections.Map.*

interface Species<out V: Any>: Sequence<Map.Entry<UUID, V>> {
    override fun iterator(): Iterator<Entry<UUID, V>>
    operator fun get(uuid: UUID): V?
}