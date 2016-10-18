package su.jfdev.anci.registry.registrar

import su.jfdev.anci.registry.*
import su.jfdev.anci.registry.exceptions.*
import su.jfdev.anci.rules.*

interface Registrar<S, R: Registration<R>> {
    val registry: Registry<R>
    val sources: Map<S, R>

    /**
     * 1) Transform [source] to [R]
     * 2) Add [R] to [registry], when [R] is valid and filtered.
     * @return [R] or null, if [source] was denied
     * @throws RegistrationConflictException when [source] already registered
     */
    infix fun register(source: S): R?

    /**
     * Remove [registration] from [registry]
     * @return false if [registration] already unregistered
     * @throws RegistrationConflictException if [Unique.uuid] bound by other session
     */
    infix fun unregister(registration: R): Boolean
}