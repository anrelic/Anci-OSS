package su.jfdev.anci.registry

import su.jfdev.anci.registry.registrar.*

interface RegistrarWrapper<S, R: Registration<R>>: Registrar<S, R> {
    val registrar: Registrar<S, R>
    override val registry: Registry<R> get() = registrar.registry
    override val sources: Map<S, R> get() = registrar.sources
    override fun register(source: S) = registrar.register(source)
    override fun unregister(registration: R) = registrar.unregister(registration)
}