package su.jfdev.anci.registry

import su.jfdev.anci.registry.exceptions.*
import su.jfdev.anci.rules.*

interface Registration<T: Registration<T>>: Unique {
    /**
     * Registry, that contains given registration
     * @throws RegistrationInvalidException - when already removed from registry
     */
    val registry: Registry<T>

    /**
     * When registration in registry
     */
    val inRegistry: Boolean
}