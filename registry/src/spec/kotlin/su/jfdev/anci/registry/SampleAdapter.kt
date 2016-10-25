package su.jfdev.anci.registry

import su.jfdev.anci.registry.registrar.*
import su.jfdev.anci.util.syntax.*

object SampleAdapter: RegistrarAdapter<String, Sample> by RegistrarAdapter({ SampleHandler(reify(), it) })

