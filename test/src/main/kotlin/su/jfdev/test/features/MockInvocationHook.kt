package su.jfdev.test.features

import com.nhaarman.mockito_kotlin.*
import io.kotlintest.matchers.*
import org.mockito.invocation.*
import kotlin.test.*

inline fun fireHook(hook: () -> Any) {
    whenever(hook()).then {
        throw MockInvocationHook(it)
    }
}

inline infix fun BeWrapper<out Any>.hooked(block: () -> Unit) = value hook block
inline infix fun Any.hook(block: () -> Unit): MockInvocationHook = try {
    block()
    fail("Given block not use mock $this")
} catch (e: MockInvocationHook) {
    if (this == e.invocation.mock) e
    else throw e
}

class MockInvocationHook(val invocation: InvocationOnMock): Throwable() {
    operator fun component1(): Any = get(0)
    operator fun component2(): Any = get(1)
    operator fun component3(): Any = get(2)
    operator fun component4(): Any = get(3)
    operator fun component5(): Any = get(4)
    operator fun component6(): Any = get(5)

    operator fun <T> get(num: Int): T = invocation.getArgument(num)
    operator fun invoke(): Any? = invocation.callRealMethod()
}