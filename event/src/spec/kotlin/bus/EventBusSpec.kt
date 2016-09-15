package bus

import io.kotlintest.matchers.*
import io.kotlintest.specs.*
import su.jfdev.anci.event.*
import su.jfdev.test.features.*
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
                Repeat.second {
                    target should have value 145
                }
            }
        }
    }

    override val oneInstancePerTest = true
}

