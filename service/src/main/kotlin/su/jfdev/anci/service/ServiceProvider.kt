package su.jfdev.anci.service

import su.jfdev.anci.service.ServiceProvider.Service.*
import kotlin.concurrent.*

open class ServiceProvider<T: Any> @JvmOverloads constructor(protected val clazz: Class<T>, val way: Service = LAZY) {
    val service: T by when (way) {
        LAZY      -> lazy { new }
        SINGLETON -> lazyOf(new)

        PROVIDER  -> object: Lazy<T> {
            override val value: T get() = new

            override fun isInitialized(): Boolean = false
        }
        THREAD_LOCAL -> object: Lazy<T> {
            private val reference: ThreadLocal<T> = ThreadLocal()

            override fun isInitialized(): Boolean = reference.get() != null

            override val value: T get() = reference.getOrSet { new }
        }
    }

    private val new: T get() = clazz.service

    enum class Service {
        PROVIDER,
        SINGLETON,
        LAZY,
        THREAD_LOCAL
    }
}
