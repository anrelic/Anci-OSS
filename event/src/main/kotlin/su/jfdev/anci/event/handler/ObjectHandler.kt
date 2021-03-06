package su.jfdev.anci.event.handler

import java.lang.reflect.*

fun <T> listeners(instance: Any, type: Class<T>): Iterable<(T) -> Unit> = instance.javaClass
        .declaredMethods
        .filter { it.isHandler }
        .map { it.listener(instance, type) }

private val Method.isHandler: Boolean get() = annotations.any { it is EventHandler || it is PrioritizedEventHandler }