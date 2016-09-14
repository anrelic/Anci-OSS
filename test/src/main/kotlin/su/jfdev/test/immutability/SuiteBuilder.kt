package su.jfdev.test.immutability

import com.google.common.collect.ImmutableSet.*
import su.jfdev.test.immutability.Immutability.*

class SuiteBuilder<T> {
    private val sub: Builder<Immutability<T>> = builder()

    infix fun append(immutability: Immutability<T>) = apply {
        sub.add(immutability)
    }

    operator fun invoke(name: String) = Suite(name, sub.build())

    companion object {
        fun <T> build(name: String, builder: SuiteBuilder<T>.() -> Unit) = SuiteBuilder<T>()
                .apply(builder)("$name should be immutable")
    }
}
