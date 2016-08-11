package su.jfdev.anci.service

import java.lang.System.*
import java.util.ServiceLoader.*

@Service(implementations = arrayOf("impl"))
interface ServiceCollector {
    /**
     * Collect services to ServiceRegistry
     */
    infix fun collect(source: ServiceSource): ServiceLoader

    /**
     * internal: `META-INF/services`
     * external: `./services`
     */

    object Default {
        private val SERVICE_CLASS = ServiceCollector::class.java
        val DEFAULT_SOURCE = ServiceSource(internal = propertySet("services.internal", "META-INF/services"),
                                           external = propertySet("services.external", "services"))

        private fun propertySet(key: String, def: String) = setOf(getProperty(key, def))
        internal fun loadByMeta(): ServiceCollector {
            val metaCollector = load(SERVICE_CLASS).firstOrNull()
                                ?: error("Undefined ServiceCollector in META-INF/services")
            val metaLoader = metaCollector collect DEFAULT_SOURCE
            return metaLoader[SERVICE_CLASS].firstOrNull() ?: metaCollector
        }
    }

    companion object: ServiceCollector by Default.loadByMeta() {
        val default: ServiceLoader = collect(Default.DEFAULT_SOURCE)
    }
}

