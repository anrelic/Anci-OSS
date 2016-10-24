package su.jfdev.anci.registry

import su.jfdev.anci.*
import java.util.*

interface RegistryWrapper<R: Identified>: Registry<R> {
    val registry: Registry<R>
    override val type: Class<R> get() = registry.type
    override val size: Int get() = registry.size
    override val values: Set<R> get() = registry.values
    override fun contains(uuid: UUID) = uuid in registry
    override fun get(uuid: UUID) = registry[uuid]
}