package su.jfdev.anci.registry.exceptions

import su.jfdev.anci.registry.*

class RegistrationInvalidException(registry: Registry<*>): RegistryException(registry)