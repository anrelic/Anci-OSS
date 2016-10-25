package su.jfdev.anci.registry.exceptions

import su.jfdev.anci.registry.*

open class RegistrarException(type: Class<out Registration<*>>): RuntimeException("Registry for $type")