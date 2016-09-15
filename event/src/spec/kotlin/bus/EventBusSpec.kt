package bus

import io.kotlintest.matchers.*
import io.kotlintest.specs.*
import su.jfdev.anci.event.*
import su.jfdev.test.features.Repeatable.Companion.millis
import su.jfdev.test.matchers.*
import java.util.concurrent.atomic.*

abstract class EventBusSpec(val bus: EventBus<Int>): FreeSpec() {
    init {
        "should handle all registered listeners" - {
            val target = AtomicInteger()
            bus.register {
                target.set(it)
            }
            "by sync" {
                bus sync 133
                target should have value 133
            }
            "by handle" {
                bus handle 145
                millis(100) {
                    target should have value 145
                } repeat 5
            }
        }
    }

    override val oneInstancePerTest = true
}

