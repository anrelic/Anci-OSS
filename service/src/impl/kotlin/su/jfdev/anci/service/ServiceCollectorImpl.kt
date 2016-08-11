package su.jfdev.anci.service

import kotlinx.support.jdk8.collections.*
import su.jfdev.anci.util.*
import su.jfdev.anci.util.stream.*
import java.io.*
import java.nio.file.*
import java.util.stream.*

class ServiceCollectorImpl: ServiceCollector {
    override fun collect(source: ServiceSource): ServiceLoader {
        val services = source.paths.services()
        return MapServiceLoader(source, services)
    }

    private val ServiceSource.paths: Stream<Path> get() = internalPaths + externalPaths

    private val ServiceSource.internalPaths: Stream<Path> get() = internal.stream().flatMap {
        ServiceCollectorImpl::class.java.resources(it)
    }

    private val ServiceSource.externalPaths: Stream<Path> get() = external.stream().flatMap {
        File(it).toPath().walkOrEmpty()
    }
}
