package su.jfdev.anci.registry.exceptions

import su.jfdev.anci.registry.*

class RegistrationConflictException(type: Class<out Registration<*>>): RegistrarException(type)