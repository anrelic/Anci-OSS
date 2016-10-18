@file:JvmName("SpeciesCollector")

package su.jfdev.anci.species

inline fun <reified V: Any> species(name: String): Species<V> = SpeciesLoader[V::class.java, name]
fun <T: Any> Class<T>.species(name: String): Species<T> = SpeciesLoader[this, name]

inline fun <reified V: Any> species(): Species<V> = SpeciesLoader[V::class.java]
fun <T: Any> Class<T>.species(): Species<T> = SpeciesLoader[this]
