package bus

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.*
import su.jfdev.anci.event.*
import java.util.*

abstract class EventBusSpec {
    abstract val bus: EventBus<MutableList<String>>
    @Test fun `should handle all registered listeners`() {
        given@
        val target: MutableList<String> = Collections.synchronizedList<String>(ArrayList())
        val appended = arrayOf("first","second")
        register@
        appended.forEach { text ->
            bus.register {
                it += text
            }
        }
        handleOrSync@
        bus.sync(target)
        checkOrWait@
        Assertions.assertThat(target).contains(*appended)
    }
}

