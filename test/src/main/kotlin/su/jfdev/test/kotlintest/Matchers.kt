@file:Suppress("NOTHING_TO_INLINE")

package su.jfdev.test.kotlintest

import io.kotlintest.matchers.*
import java.util.concurrent.atomic.*

object __DummyForMatchers: Matchers

inline fun <R> matching(block: Matchers.() -> R): R = __DummyForMatchers.block()

inline infix fun <T> ContainWrapper<out Collection<T>>.only(elements: Array<T>) = only(elements.toList())
inline infix fun <T> ContainWrapper<out Collection<T>>.only(elements: Iterable<T>) = only(elements.toList())
inline infix fun <T> ContainWrapper<out Collection<T>>.only(elements: Collection<T>) = matching {
    value should have size elements.size
    value should contain all elements
}

inline infix fun <T> ContainWrapper<out Collection<T>>.all(elements: Array<T>) = matching {
    for (element in elements) element(element)
}

inline infix fun <T> ContainWrapper<out Collection<T>>.all(elements: Iterable<T>) = matching {
    for (element in elements) element(element)
}

inline infix fun HaveWrapper<AtomicInteger>.value(expected: Int) = matching {
    value.get() shouldBe expected
}

inline infix fun <T> HaveWrapper<AtomicReference<T>>.value(expected: T) = matching {
    value.get() shouldBe expected
}