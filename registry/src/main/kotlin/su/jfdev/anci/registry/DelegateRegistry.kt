package su.jfdev.anci.registry

import su.jfdev.anci.rules.*
import su.jfdev.anci.util.*
import java.util.*

class DelegateRegistry<R: Unique>(override val type: Class<R>, private val delegate: Map<UUID, R>): Registry<R> {
    override val size: Int get() = delegate.size
    override val isEmpty: Boolean get() = delegate.isEmpty()
    override val values: Set<R> = UnmodifiableValuesSet(delegate.values)
    override fun contains(uuid: UUID) = uuid in delegate
    override fun get(uuid: UUID) = delegate[uuid]

    companion object {
        operator inline fun <reified R: Unique> get(delegate: Map<UUID, R>): Registry<R> = DelegateRegistry(reify(), delegate)
    }
}