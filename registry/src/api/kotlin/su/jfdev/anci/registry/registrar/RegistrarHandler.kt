package su.jfdev.anci.registry.registrar

import su.jfdev.anci.registry.*
import su.jfdev.anci.registry.exceptions.*

interface RegistrarHandler<S, R: Registration<R>> {
    val registrar: Registrar<S, R>

    infix fun from(source: S): R

    /**
     * When try register [source], which is already in registry.
     * As default throw [RegistrationConflictException]
     * @return registration to return by [Registrar.register]
     */
    infix fun conflict(source: S): R? = throwConflict()


    /**
     * When try unregister [registration] and registry contains [Registration] with same uuid
     * As default throw [RegistrationConflictException]
     * @return result to return by [Registrar.unregister]
     */
    infix fun conflict(registration: R): Boolean = throwConflict()

    /**
     * When try register invalid [source]
     * As default throw [RegistrationInvalidException]
     * @return source to continue
     */
    infix fun invalid(source: S): S = throwInvalid()

    /**
     * When try register invalid [registration] before add to registry
     * As default throw [RegistrationInvalidException]
     * @return registration to return by [Registrar.register]
     */
    infix fun invalid(registration: R): R? = throwInvalid()

    /**
     * Validate when try register [source]
     */
    infix fun validate(source: S) = true

    /**
     * Validate when try register [registration]
     */
    infix fun validate(registration: R) = registration.inRegistry

    /**
     * Execute when finish unregister [registration]
     */
    infix fun unregister(registration: R): Unit = Unit


    private companion object {
        private fun RegistrarHandler<*, *>.throwConflict(): Nothing = throw RegistrationConflictException(registrar.registry)
        private fun RegistrarHandler<*, *>.throwInvalid(): Nothing = throw RegistrationInvalidException(registrar.registry)
    }
}