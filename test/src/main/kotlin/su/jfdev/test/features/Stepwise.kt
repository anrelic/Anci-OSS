package su.jfdev.test.features

import io.kotlintest.*
import io.kotlintest.TestSuite.Companion.empty
import io.kotlintest.specs.*
import su.jfdev.anci.util.syntax.*
import java.util.concurrent.atomic.*
import kotlin.reflect.*
import kotlin.test.*

interface Stepwise {
    fun after(action: String, block: () -> Unit)
    fun test(action: String, block: () -> Unit): TestCase

    fun checking(action: String, block: () -> Boolean) = after(action) {
        assertTrue(action, block)
    }

    fun checkingNot(action: String, block: () -> Boolean) = after(action) {
        assertFalse(action, block)
    }

    fun expect(action: String, expected: Any?, block: () -> Any?): TestCase {
        val message = "expect $action"
        return test(message) {
            assertEquals(expected, block(), message)
        }
    }

    fun should(action: String, block: () -> Boolean): TestCase {
        val message = "should $action"
        return test(message) {
            assertTrue(message, block)
        }
    }

    fun shouldNot(action: String, block: () -> Boolean): TestCase {
        val message = "should not $action"
        return test(message) {
            assertFalse(message, block)
        }
    }


    fun catch(expect: KClass<out Throwable>, way: String, block: () -> Unit): TestCase {
        val message = "should throw ${expect.simpleName} when $way"
        return test(message) {
            assertFailsWith(expect, message, block)
        }
    }

    fun <R> take(action: String, block: () -> R) = AtomicReference<R>().apply {
        after(action) {
            set(block())
        }
    }

    fun <R> AtomicReference<R>.should(action: String, block: R.() -> Boolean) = this@Stepwise.should(action) {
        get().block()
    }

    fun <R> AtomicReference<R>.shouldNot(action: String, block: R.() -> Boolean) = this@Stepwise.shouldNot(action) {
        get().block()
    }

    class Session(root: TestSuite, name: String): Stepwise {
        private var last = BeforeAction(suite = empty(name)).apply {
            root.nestedSuites += suite
        }

        private inner class BeforeAction(val suite: TestSuite, val block: () -> Unit = {}) {
            val requirements: TestSuite by lazy {
                empty("expect") finally {
                    suite.nestedSuites += it
                }
            }
        }

        private inner class Requirement(name: String, val block: () -> Unit) {
            private val before = last
            val case = TestCase(suite = before.requirements, name = name, config = TestConfig(), test = {
                before.block()
                block()
            }) finally {
                it.suite.cases += it
            }
        }

        override fun after(action: String, block: () -> Unit) {
            val parent = last
            last = BeforeAction(suite = empty("after $action")) {
                parent.block()
                block()
            }
            parent.suite.nestedSuites += last.suite
        }


        override fun test(action: String, block: () -> Unit) = Requirement(action, block).case
    }

    interface Feature {
        var current: TestSuite

        fun stepwise(name: String, stepwise: Stepwise.() -> Unit) {
            Session(current, name).stepwise()
        }

    }

    abstract class Spec: FreeSpec(), Feature
}
