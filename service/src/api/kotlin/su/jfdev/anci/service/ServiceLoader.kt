package su.jfdev.anci.service

@Service(implementations = arrayOf("impl"))
interface ServiceLoader {
    val source: ServiceSource

    fun with(source: ServiceSource): ServiceLoader

    /**
     * Lazily initialized services with checking and casting to [clazz] type
     */
    operator fun <T: Any> get(clazz: Class<T>): Sequence<T>

    /**
     * Lazily initialized services without checking and casting to any type, where [name] is filename
     */
    operator fun get(name: String): Sequence<Any>


    object Default {

        /**
         * @internal `META-INF/services`
         * @external `./services`
         */
        val SOURCE = ServiceSource.metaInf("services")

        internal fun loadByMeta(): ServiceLoader {
            val metaLoader = java.util.ServiceLoader.load(ServiceLoader::class.java).firstOrNull()
                             ?: error("Undefined ServiceLoader in META-INF/services")
            return metaLoader[ServiceLoader::class.java].firstOrNull() ?: metaLoader
        }
    }

    companion object: ServiceLoader by Default.loadByMeta()
}
