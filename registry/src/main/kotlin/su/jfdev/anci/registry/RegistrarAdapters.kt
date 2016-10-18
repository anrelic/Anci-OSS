package su.jfdev.anci.registry

import su.jfdev.anci.registry.registrar.*
import su.jfdev.anci.registry.service.*

inline fun <S, reified R: Registration<R>> RegistrarAdapter(crossinline handler: (Registrar<S, R>) -> RegistrarHandler<S, R>) = object: RegistrarAdapter<S, R> {
    override val type: Class<R> = R::class.java
    override fun handler(registrar: Registrar<S, R>) = handler(registrar)
}

inline fun <S, reified R: Registration<R>> RegistrarFactory(crossinline handler: Registrar<S, R>.(S) -> R) = object: RegistrarAdapter<S, R> {
    override val type: Class<R> = R::class.java
    override fun handler(registrar: Registrar<S, R>) = registrar.RegistrarHandler(handler)
}

inline fun <S, R: Registration<R>> Registrar<S, R>.RegistrarHandler(crossinline handler: Registrar<S, R>.(S) -> R) = object: RegistrarHandler<S, R> {
    override val registrar: Registrar<S, R> get() = this@RegistrarHandler
    override fun from(source: S): R = handler(source)
}

inline fun <S, reified R: Registration<R>> RegistryService.registrar(crossinline handler: Registrar<S, R>.(S) -> R)
        = registrar(adapter = RegistrarFactory(handler))

inline fun <S, reified R: Registration<R>> RegistryService.customize(crossinline handler: (Registrar<S, R>) -> RegistrarHandler<S, R>)
        = registrar(adapter = RegistrarAdapter(handler))