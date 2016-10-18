package su.jfdev.anci.registry.registrar

import su.jfdev.anci.event.*
import su.jfdev.anci.event.phase.*
import su.jfdev.anci.registry.*
import su.jfdev.anci.registry.registrar.RegistryEvent.*

interface RegistryEvent: Event<Reason> {
    val registration: Registration<*>

    enum class Reason {
        Register,
        Unregister
    }

    companion object Bus: EventCompanion<RegistryEvent>()
}