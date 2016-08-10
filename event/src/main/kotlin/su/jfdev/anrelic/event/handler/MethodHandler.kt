@file:Suppress("NOTHING_TO_INLINE")

package su.jfdev.anrelic.event.handler

import su.jfdev.anrelic.event.*
import su.jfdev.anrelic.event.Prioritized.*
import java.lang.invoke.*
import java.lang.invoke.MethodHandles.*
import java.lang.reflect.*
import kotlin.reflect.*
import kotlin.reflect.jvm.*


fun <T> KFunction<Unit>.listener(instance: Any, type: Class<T>): (T) -> Unit = javaMethod.run {
    this ?: error("$this cannot been used as handler becayse is not Java method")
    listener(instance, type)
}

private val lookup: Lookup = MethodHandles.lookup()
fun <T> Method.listener(instance: Any, type: Class<T>): (T) -> Unit = object: (T) -> Unit, Prioritized {
    init {
        val receiver = parameterTypes.single()
        require(type instanceOf receiver) {
            val `return` = returnType.simpleName
            val argument = receiver.simpleName
            "$`return` $name($argument) cannot been used as handler"
        }
    }

    private val handle by lazy {
        lookup.unreflect(this@listener)
    }

    override val priority: Priority = getAnnotation(PrioritizedEventHandler::class.java)?.priority ?: Priority.DEFAULT

    override fun invoke(event: T) {
        handle.invokeWithArguments(instance, event)
    }


    private inline infix fun Class<*>.instanceOf(clazz: Class<*>) = clazz.isAssignableFrom(this)
                                                             || kotlin.javaObjectType == clazz
                                                             || clazz.kotlin.javaObjectType == this
}
