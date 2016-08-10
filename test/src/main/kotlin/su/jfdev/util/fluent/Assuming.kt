package su.jfdev.util.fluent

import org.junit.jupiter.api.*

data class Assuming(val condition: () -> Boolean)
infix fun Assuming.then(action: () -> Unit) {
    Assumptions.assumingThat(condition, action)
}