package su.jfdev.anci.registry.service

import su.jfdev.anci.registry.*
import su.jfdev.anci.registry.registrar.*

object RegistryServiceImpl: RegistryService {
    override fun <S, R: Registration<R>> registrar(adapter: RegistrarAdapter<S, R>): Registrar<S, R> = RegistrarImpl(adapter)
}