package su.jfdev.test.features

import java.util.concurrent.atomic.*

class StepResult<R>(val reference: AtomicReference<R>) {
    val result: R get() = reference.get()

    fun use(stepwise: R.() -> Unit) = then {
        it.stepwise()
    }

    fun then(stepwise: StepResult<R>.(R) -> Unit) = apply {
        stepwise(result)
    }
}