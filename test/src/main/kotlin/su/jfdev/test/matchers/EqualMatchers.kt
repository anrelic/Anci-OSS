@file:JvmMultifileClass
@file:JvmName("Matchers")
@file:Suppress("NOTHING_TO_INLINE")

package su.jfdev.test.matchers

import io.kotlintest.matchers.*

inline infix fun <T> BeWrapper<T>.equal(expected: T) = matching {
    value shouldEqual expected
}

inline infix fun <T> BeWrapper<T>.same(expected: T) = matching {
    value shouldBe expected
}