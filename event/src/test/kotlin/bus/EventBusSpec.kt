package bus

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.*
import su.jfdev.anci.event.*
import su.jfdev.anci.event.stream.*
import java.util.*
import java.util.concurrent.*

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

class SimpleEventBusSpec: EventBusSpec() {
    override val bus: EventBus<MutableList<String>> = SimpleEventBus(StreamEventLoop, CopyOnWriteArraySet())
}

