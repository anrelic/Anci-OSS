@file:Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")

package handler

import org.junit.*
import su.jfdev.anci.event.handler.*
import kotlin.test.*

class ExecuteSpec {
    var executed: Boolean = false
    @Test fun `should execute block inside method`() {
        assertFalse { executed }

        val listener: (Boolean) -> Unit = ExecuteSpec::execute.listener(this)

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