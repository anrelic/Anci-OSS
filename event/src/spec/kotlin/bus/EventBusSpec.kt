package bus

import io.kotlintest.*
import io.kotlintest.matchers.*
import io.kotlintest.specs.*
import su.jfdev.anci.event.*
import su.jfdev.test.kotlintest.*
import java.util.*

abstract class EventBusSpec(val bus: EventBus<MutableList<String>>): FreeSpec(), Eventually {
    val appended = arrayOf("first", "second")

    init {
        "should handle all registered listeners" - {
            val target = Collections.synchronizedList<String>(ArrayList())
            for (text in appended) bus.register {
                it += text
            }
            "by sync" {
                bus sync target
                target should contain only appended
            }
            "by handle" {
                bus handle target
                Repeat.second {
                    target should contain only appended
                }
            }
        }
    }

    override val oneInstancePerTest = true
}

