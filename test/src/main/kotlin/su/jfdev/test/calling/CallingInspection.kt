package su.jfdev.test.calling

import java.util.*
import kotlin.test.*

data class CallingInspection<out R>(val inspector: Inspector, val init: Inspection.() -> R) {
    private val session = UUID.randomUUID()
    private val inspection = Inspection(session)

    val result = init(inspection)

    infix fun by(action: R.() -> Unit) = inspector.repeat {
        unit(expect = it) {
            result.action()
        }
    }

    private fun unit(expect: Boolean, action: () -> Unit) {
        val throwable = try {
            action()
            null
        } catch (e: Throwable) {
            e
        }
        when {
            expect == inspection inspect throwable -> return
            expect                                 -> fail("Process should call inspection")
            else                                   -> fail("Process should not call inspection")
        }
    }

}