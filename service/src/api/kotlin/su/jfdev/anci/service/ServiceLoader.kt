package su.jfdev.anci.service

import su.jfdev.anci.service.ServiceCollector.*

interface ServiceLoader {
    val source: ServiceSource
    /**
     * Lazily initialized services with checking and casting to [clazz] type
     */
    operator fun <T: Any> get(clazz: Class<T>): Sequence<T>

    /**
     * Lazily initialized services without checking and casting to [clazz] type, where [clazz] used for extract filename
     */
    infix fun unchecked(clazz: Class<*>): Sequence<Any>

    companion object Default: ServiceLoader by Companion.default
}
