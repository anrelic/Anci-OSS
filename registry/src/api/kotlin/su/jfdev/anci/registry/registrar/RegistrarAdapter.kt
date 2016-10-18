package su.jfdev.anci.registry.registrar

import su.jfdev.anci.registry.*

interface RegistrarAdapter<S, R: Registration<R>> {
    val type: Class<R>
    fun handler(registrar: Registrar<S, R>): RegistrarHandler<S, R>
}


