package su.jfdev.anci.service

import java.lang.System.*

/**
 * @property internal - resources in runtime classpath
 * @property external - resources in
 */
data class ServiceSource(val internal: Set<String>, val external: Set<String>) {
    constructor(internal: String, external: String): this(setOf(internal), setOf(external))

    companion object Propertied {
        fun byProperty(internal: String, external: String = internal) = ServiceSource(internalProperty(internal),
                                                                                      externalProperty(external))

        fun internalProperty(internal: String, defaultInternal: String = internal): String
                = getProperty("spi.internal.$internal", defaultInternal)

        fun externalProperty(external: String, defaultExternal: String = external): String
                = getProperty("spi.external.$external", defaultExternal)

        fun metaInf(property: String) = ServiceSource(
                internalProperty(property, "META-INF/$property"),
                externalProperty(property)
                                                     )

        operator fun get(property: String) = byProperty(property)
    }
}