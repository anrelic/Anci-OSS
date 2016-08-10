package su.jfdev.anci.service

import org.apache.logging.log4j.*

class MapServiceLoader(override val source: ServiceSource, val services: Map<Class<*>, Set<Class<*>>>): ServiceLoader {
    override fun <T: Any> get(clazz: Class<T>): Sequence<T> = find(clazz).mapNotNull {
        it.convert(clazz)?.load()
    }

    override fun unchecked(clazz: Class<*>): Sequence<Any> = find(clazz).mapNotNull {
        it.load()
    }

    private fun find(clazz: Class<*>) = services[clazz].orEmpty().asSequence()

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
        val LOG: Logger = LogManager.getLogger(MapServiceLoader::class.java)
    }

}