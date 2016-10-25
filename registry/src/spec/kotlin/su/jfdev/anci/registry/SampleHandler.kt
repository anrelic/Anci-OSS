package su.jfdev.anci.registry

import su.jfdev.anci.registry.registrar.*
import java.util.UUID.*

open class SampleHandler(override val type: Class<Sample>, override val registrar: Registrar<String, Sample>): RegistrarHandler<String, Sample> {
    override fun from(source: String) = Sample(uuid = fromString(source), registry = registrar.registry)
}