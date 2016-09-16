package su.jfdev.test.features

class StepResult<R>(action: () -> R) {
    val result: R by lazy(action)

    infix fun then(stepwise: StepResult<R>.() -> Unit) = apply(stepwise)
}