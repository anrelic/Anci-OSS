package su.jfdev.anci.service

import kotlinx.support.jdk8.collections.*
import su.jfdev.anrelic.util.*
import su.jfdev.anrelic.util.stream.*
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

    override val default: ServiceLoader = this collect ServiceSource(internal = setOf(resourcesPath),
                                                                     external = setOf(servicesPath))

    private val servicesPath: String get() = System.getProperty("services.dir", "services")
    private val resourcesPath: String get() = System.getProperty("services.resources.dir", "META-INF/services")
}

