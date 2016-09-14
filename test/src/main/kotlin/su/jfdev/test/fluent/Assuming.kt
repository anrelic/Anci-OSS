package su.jfdev.test.fluent

data class Assuming(val condition: () -> Boolean)
infix fun Assuming.then(action: () -> Unit) {
    if (condition()) action()
}