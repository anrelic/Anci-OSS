@file:Suppress("NOTHING_TO_INLINE", "UNCHECKED_CAST")

package su.jfdev.util.fluent

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import kotlin.reflect.*
import kotlin.test.assertTrue as shouldTrue

inline infix fun Any?.shouldBe(any: Any?) = assertSame(any, this)
inline infix fun Any?.`should not be`(any: Any?) = assertNotSame(any, this)

inline infix fun Any?.shouldEqual(any: Any?) = assertEquals(any, this)
inline infix fun Any?.`should not equal`(any: Any?) = assertNotEquals(any, this)

infix fun <T: Throwable> KClass<T>.shouldThrow(block: () -> Unit) = assertThrows(java, block)
inline fun <reified T: Throwable> shouldThrow(noinline block: () -> Unit) = T::class shouldThrow block

fun String.shouldAll(vararg actions: () -> Unit) {
    val executables = actions.map {
        Executable(it)
    }.toTypedArray()
    return assertAll(this, *executables)
}

inline infix fun <reified T: Any> Any.shouldAs(using: (T) -> Unit) = using(shouldAs())

inline fun <reified T: Any> Any.shouldAs(): T = shouldAs(T::class)

inline infix fun <T: Any> Any.shouldAs(clazz: KClass<T>): T {
    shouldAs(clazz.java)
    return this as T
}

infix inline fun Any.shouldAs(clazz: Class<*>) = shouldTrue("$this should be instanceOf ${clazz.canonicalName}") {
    clazz.isInstance(this)
}