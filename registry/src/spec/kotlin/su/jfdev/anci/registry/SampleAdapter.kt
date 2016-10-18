package su.jfdev.anci.registry

import su.jfdev.anci.registry.registrar.*

object SampleAdapter: RegistrarAdapter<String, Sample> by RegistrarAdapter(::SampleHandler)

