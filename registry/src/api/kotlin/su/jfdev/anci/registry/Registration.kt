package su.jfdev.anci.registry

import su.jfdev.anci.*
import su.jfdev.anci.registry.exceptions.*

interface Registration<T: Registration<T>>: Identified {
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