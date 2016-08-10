package su.jfdev.anrelic.event.handler

import su.jfdev.anrelic.event.*
import su.jfdev.anrelic.event.Prioritized.*
import java.lang.reflect.*
import kotlin.annotation.AnnotationTarget.*
import kotlin.reflect.*

/**
 * Jamefrus and his team on 14.06.2016.
 */

@Target(FUNCTION)
annotation class EventHandler

@Target(FUNCTION)
annotation class PrioritizedEventHandler(val priority: Priority)

fun <T: Any> EventBus<T>.register(collection: Collection<(T) -> Unit>) = collection.forEach { register(it) }

inline fun <reified T: Any> listeners(instance: Any) = listeners(instance, T::class.java)
inline fun <reified T: Any> KFunction<Unit>.listener(instance: Any) = listener(instance, T::class.java)
inline fun <reified T: Any> Method.listener(instance: Any) = listener(instance, T::class.java)