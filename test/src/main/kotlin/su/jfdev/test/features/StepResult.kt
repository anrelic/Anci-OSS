package su.jfdev.test.features

import su.jfdev.anci.util.*

class StepResult<R>(action: () -> R) {
    val result: R by SuppliedThreadLocal(action)

    infix fun then(stepwise: StepResult<R>.() -> Unit) = apply(stepwise)
}