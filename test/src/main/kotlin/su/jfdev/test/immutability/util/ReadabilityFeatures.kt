package su.jfdev.test.immutability.util

import su.jfdev.test.immutability.*

fun <T, F> Immutability<T>.cannot(function: String, map: F.() -> T) = transform(name = "$function should be immutable",
                                                                                transform = map)

fun <T, F> SuiteBuilder<F>.cannot(immutability: Immutability<T>, function: String, map: F.() -> T) = append {
    immutability.cannot(function, map)
}

inline fun <T> SuiteBuilder<T>.by(name: String, immutability: () -> Immutability<T>) = append {
    immutability() rename "as $name"
}