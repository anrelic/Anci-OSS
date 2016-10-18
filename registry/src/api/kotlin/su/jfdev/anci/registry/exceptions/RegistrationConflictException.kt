package su.jfdev.anci.registry.exceptions

import su.jfdev.anci.registry.*

class RegistrationConflictException(registry: Registry<*>): RegistryException(registry)