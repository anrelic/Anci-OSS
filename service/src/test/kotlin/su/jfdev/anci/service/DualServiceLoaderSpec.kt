package su.jfdev.anci.service

import su.jfdev.anci.util.*

class DualServiceLoaderSpec: ServiceLoaderSpec<Any>(reify(), loader = ServiceLoaderImpl())