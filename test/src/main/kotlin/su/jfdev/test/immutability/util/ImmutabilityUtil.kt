package su.jfdev.test.immutability.util

import su.jfdev.test.immutability.*
import su.jfdev.test.immutability.Immutability.*
import su.jfdev.test.immutability.SpecAdapter.*

operator fun <T> Immutability<T>.get(checked: T) = this(Empty) { checked }
fun <T, F> Immutability<T>.transform(name: String = this.name, transform: (F) -> T) = Transform(name, transform, this)