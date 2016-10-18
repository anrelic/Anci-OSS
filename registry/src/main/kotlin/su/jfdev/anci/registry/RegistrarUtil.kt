package su.jfdev.anci.registry

import su.jfdev.anci.registry.registrar.*

infix fun <S, R: Registration<R>> Registrar<S, R>.unregister(source: S): Boolean {
    val reg = sources[source]
    return reg != null && unregister(reg)
}