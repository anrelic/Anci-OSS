package su.jfdev.anci.service

import su.jfdev.anrelic.util.*

class DualServiceLoaderSpec: ServiceLoaderSpec<Any>(reify(), collector = ServiceCollectorImpl())