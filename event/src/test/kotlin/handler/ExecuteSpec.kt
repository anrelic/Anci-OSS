@file:Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")

package handler

import org.junit.jupiter.api.*
import su.jfdev.anrelic.event.handler.*
import kotlin.test.*

class ExecuteSpec {
    var executed: Boolean = false
    @Test fun `should execute block inside method`() {
        given@
        assertFalse { executed }

        whenn@
        val listener: (Boolean) -> Unit = ExecuteSpec::execute.listener(this)

        then@
        listener(true)
        assertTrue { executed }

        listener(false)
        assertFalse { executed }

        listener(false)
        assertFalse { executed }
    }

    @EventHandler fun execute(state: Boolean) {
        executed = state
    }
}