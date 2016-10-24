package su.jfdev.anci.registry.registrar

import su.jfdev.anci.registry.*
import su.jfdev.anci.registry.registrar.RegistryEvent.*
import su.jfdev.anci.registry.registrar.RegistryEvent.Reason.*
import su.jfdev.anci.util.*
import su.jfdev.anci.util.syntax.*
import java.util.*
import java.util.concurrent.*

class RegistrarImpl<F, R: Registration<R>>(adapter: RegistrarAdapter<F, R>): Registrar<F, R> {
    private val _registry = ConcurrentHashMap<UUID, R>()
    private val _sources = ConcurrentHashMap<F, R>()

    override val registry = DelegateRegistry(type = adapter.type, delegate = _registry)
    override val sources = _sources.unmodifiable()

    private val handler = adapter.handler(this)

    override fun register(source: F): R? = when {
        source in sources.keys  -> handler conflict source
        handler validate source -> register(source = source, registration = handler from source)
        else                    -> register(handler invalid source)
    }

    private fun register(registration: R, source: F) = when {
        handler validate registration -> add(registration, source)
        else                          -> handler invalid registration
    }

    private fun add(reg: R, source: F) = reg.apply {
        _registry[uuid] = reg
        _sources[source] = reg
        push(Register)
    }

    override fun unregister(registration: R) = when {
        _registry.remove(registration.uuid, registration) -> unregisterAbsent(registration)
        registration.uuid in registry                     -> handler conflict registration
        else                                              -> false
    }

    private fun unregisterAbsent(registration: R) = _sources.values.remove(registration) && true finally {
        handler unregister registration
        registration push Unregister
    }

    private infix fun R.push(event: Reason) = RegistryEvent push object: RegistryEvent {
        override val registration: Registration<*> = this@push
        override val phase: Reason = event
    }
}

