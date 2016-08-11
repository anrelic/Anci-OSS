package su.jfdev.anci.util.stream

import java.util.stream.*

@Suppress("UNCHECKED_CAST")
fun <T> Stream<T?>.filterNotNull(): Stream<T> = filter { it != null } as Stream<T>

operator fun <T> Stream<T>.plus(other: Stream<T>): Stream<T> = Stream.concat(this, other)!!