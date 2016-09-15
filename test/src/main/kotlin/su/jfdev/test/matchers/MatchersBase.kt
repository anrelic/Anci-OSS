@file:JvmMultifileClass
@file:JvmName("Matchers")
@file:Suppress("NOTHING_TO_INLINE")

package su.jfdev.test.matchers

import io.kotlintest.matchers.*

object __DummyForMatchers: Matchers

inline fun <R> matching(block: Matchers.() -> R): R = block(__DummyForMatchers)