@file:Suppress("NOTHING_TO_INLINE", "UNCHECKED_CAST")

package su.jfdev.test.fluent

import org.hamcrest.*
import org.junit.Assert.*
import kotlin.reflect.*
import kotlin.test.*
import kotlin.test.assertTrue as shouldTrue

inline infix fun <T> T.should(matcher: Matcher<T>) = assertThat(this, matcher)

inline infix fun Any?.shouldBe(any: Any?) = assertSame(any, this)
inline infix fun Any?.`should not be`(any: Any?) = assertNotSame(any, this)

inline infix fun Any?.shouldEqual(any: Any?) = assertEquals(any, this)
inline infix fun Any?.`should not equal`(any: Any?) = assertNotEquals(any, this)

infix fun <T: Throwable> KClass<T>.shouldThrow(block: () -> Unit) = assertFailsWith(this, block)
inline fun <reified T: Throwable> shouldThrow(noinline block: () -> Unit) = T::class shouldThrow block