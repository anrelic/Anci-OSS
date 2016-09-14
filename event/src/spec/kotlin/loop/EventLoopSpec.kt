package loop

import org.junit.jupiter.api.*
import su.jfdev.anci.event.*
import su.jfdev.anci.event.Prioritized.*
import su.jfdev.anci.event.Prioritized.Priority.*
import su.jfdev.test.fluent.*

abstract class EventLoopSpec {
    abstract val eventLoop: EventLoop

    @Test fun `should send event to listener`() {
        var received: String = "Other"
        val listener = { event: String ->
            received = event
        }
        eventLoop.handle(listOf(listener), "String")
        repeat(100) {
            if (received != "String") Thread.sleep(10) else return
        }
    }

    @Test fun `should send event to listener with wait`() {
        var received: String = "Other"
        val listener = { event: String ->
            received = event
        }
        eventLoop.sync(listOf(listener), "String")
        received shouldEqual "String"
    }

    @Test fun `More prioritized handler should be executed early`() {
        var number = 0
        val more = PrioritizedHandler(High) {
            number++ shouldBe 0
        }
        val less = PrioritizedHandler(Medium) {
            number shouldBe 1
            number += 2
        }
        val min = PrioritizedHandler(Low) {
            number shouldBe 3
            number += 100
        }
        eventLoop.sync(listOf(less, min, more), Unit)

        number shouldBe 103
    }

    class PrioritizedHandler(override val priority: Priority,
                             val listener: (Unit) -> Unit): Prioritized, (Unit) -> Unit by listener
}

