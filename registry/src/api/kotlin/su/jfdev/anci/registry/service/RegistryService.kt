package su.jfdev.anci.registry.service

import su.jfdev.anci.registry.*
import su.jfdev.anci.registry.registrar.*
import su.jfdev.anci.service.*

@Service interface RegistryService {
    fun <S, R: Registration<R>> registrar(adapter: RegistrarAdapter<S, R>): Registrar<S, R>

    companion object: RegistryService by service()
}