package su.jfdev.test.features

import io.kotlintest.properties.*
import io.kotlintest.specs.*
import java.util.concurrent.atomic.*
import kotlin.test.*

fun <R> FreeSpec.shared(declaration: String, block: () -> R) = AtomicReference<R>().apply {
    declaration {
        set(block())
    }
}

fun FreeSpec.during(count: Int, block: () -> Unit) = repeat(count) {
    "Attempt $it" - block
}

infix fun <T> Gen<T>.each(block: T.() -> Unit) = repeat(1000) {
    generate().block()
}

fun none(block: () -> Unit) = assertFailsWith(AssertionError::class, block)