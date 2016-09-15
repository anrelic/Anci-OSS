@file:JvmMultifileClass
@file:JvmName("Matchers")
@file:Suppress("NOTHING_TO_INLINE")

package su.jfdev.test.matchers

import io.kotlintest.matchers.*
import java.util.concurrent.atomic.*

inline infix fun HaveWrapper<AtomicInteger>.value(expected: Int) = matching {
    value.get() shouldBe expected
}

inline infix fun HaveWrapper<AtomicBoolean>.value(expected: Boolean) = matching {
    value.get() shouldBe expected
}

inline infix fun HaveWrapper<AtomicLong>.value(expected: Long) = matching {
    value.get() shouldBe expected
}

inline infix fun <T> HaveWrapper<AtomicReference<T>>.value(expected: T) = matching {
    value.get() shouldBe expected
}