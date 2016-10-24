package su.jfdev.anci.service

import su.jfdev.anci.util.syntax.*

class DualServiceLoaderSpec: ServiceLoaderSpec<Any>(reify(), loader = ServiceLoaderImpl())