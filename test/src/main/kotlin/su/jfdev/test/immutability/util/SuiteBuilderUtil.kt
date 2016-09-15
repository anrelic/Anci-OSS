package su.jfdev.test.immutability.util

import su.jfdev.test.immutability.*
import su.jfdev.test.immutability.Immutability.*
import kotlin.reflect.*
import kotlin.test.*

//TODO: configure expected exception
fun <T> SuiteBuilder<T>.cannot(action: String, validator: (T) -> Unit) = fail("cannot $action",
                                                                              Exception::class, validator)

fun <T> SuiteBuilder<T>.fail(action: String, with: KClass<out Throwable>, validator: (T) -> Unit) = validate(action) {
    assertFailsWith(with) {
        validator(it)
    }
}

fun <T> SuiteBuilder<T>.validate(action: String, validator: (T) -> Unit) = append {
    Test(action, validator)
}

inline fun <T> SuiteBuilder<T>.append(factory: () -> Immutability<T>) = append(factory())