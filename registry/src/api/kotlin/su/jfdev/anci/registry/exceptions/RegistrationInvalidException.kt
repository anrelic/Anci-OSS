package su.jfdev.anci.registry.exceptions

import su.jfdev.anci.registry.*

class RegistrationInvalidException(type: Class<out Registration<*>>): RegistrarException(type)