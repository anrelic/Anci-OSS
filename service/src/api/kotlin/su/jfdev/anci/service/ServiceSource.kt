package su.jfdev.anci.service

/**
 * @property internal - resources in runtime classpath
 * @property external - resources in
 */
data class ServiceSource(val internal: Set<String>, val external: Set<String>)