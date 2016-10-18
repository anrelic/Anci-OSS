package su.jfdev.anci.registry.exceptions

import su.jfdev.anci.registry.*

open class RegistryException(val registry: Registry<*>): RuntimeException("Registry for ${registry.type}")