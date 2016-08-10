@file:Suppress("NOTHING_TO_INLINE")
@file:JvmName("Services")

package su.jfdev.anci.service

import kotlin.reflect.*

inline fun <T: Any> KClass<T>.service() = java.service
inline fun <reified T: Any> service() = T::class.java.service

val <T: Any> KClass<T>.service: T get() = java.service
val <T: Any> Class<T>.service: T get() = ServiceLoader[this].first()