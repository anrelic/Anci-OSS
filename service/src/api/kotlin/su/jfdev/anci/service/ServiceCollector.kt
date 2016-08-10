package su.jfdev.anci.service

import java.util.ServiceLoader as JLoader

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
    val default: ServiceLoader

    companion object: ServiceCollector by
                      run({
                              val CLASS = ServiceCollector::class.java
                              val loader = JLoader.load(CLASS).firstOrNull()
                              val primary = loader ?: error("Undefined ServiceCollector in META-INF/services")
                              val secondary = primary.default[ServiceCollector::class.java]
                              secondary.firstOrNull() ?: primary
                          })
}

