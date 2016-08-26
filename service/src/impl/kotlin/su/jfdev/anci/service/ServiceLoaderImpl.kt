package su.jfdev.anci.service

import org.apache.logging.log4j.*
import su.jfdev.anci.service.ServiceLoader.Default.SOURCE

class ServiceLoaderImpl(override val source: ServiceSource): ServiceLoader {
    override fun with(source: ServiceSource): ServiceLoader = ServiceLoaderImpl(source)
    constructor(): this(SOURCE)

    val services: Map<String, Set<Class<*>>> = source.paths.services()

    override fun <T: Any> get(clazz: Class<T>): Sequence<T> = find(clazz.canonicalName).mapNotNull {
        it.convert(clazz)?.load()
    }

    override fun get(name: String): Sequence<Any> = find(name).mapNotNull {
        it.load()
    }

    private fun find(name: String) = services[name].orEmpty().asSequence()

    private fun <T> Class<*>.convert(clazz: Class<T>): Class<out T>? = when(clazz.isAssignableFrom(this)){
        true -> asSubclass(clazz)
        else -> {
            LOG.warn("Cannot cast $this to $clazz")
            null
        }
    }

    private fun <T: Any> Class<out T>.load(): T? = try {
        kotlin.objectInstance ?: newInstance()
    } catch (e: Exception){
        LOG.warn("Failed when initialize $this", e)
        null
    }

    companion object {
        val LOG: Logger = LogManager.getLogger(ServiceLoaderImpl::class.java)
    }

}