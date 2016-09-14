@file:Suppress("UNUSED_PARAMETER", "unused")

package handler

import org.junit.*
import su.jfdev.anci.event.handler.*
import java.util.*
import kotlin.test.*

class ValidateSpec {
    @Test fun `should fail when invalid method type`() {
        assertFailsWith(IllegalArgumentException::class) {
            ValidateSpec::nonReceiver.listener<Any>(this)
        }
    }

    @Test fun `should not fail when valid method type`() {
        ValidateSpec::nonReceiver.listener<Unit>(this)
    }

    @EventHandler fun nonReceiver(unit: Unit) = Unit

    @Test fun `should fail when invalid method struct`() {
        assertFailsWith(NoSuchElementException::class) {
            ValidateSpec::invalidStruct.listener<Unit>(this)
        }
    }

    @EventHandler fun invalidStruct() = Unit

    @Test fun `should not fail when receiver method`() {
        Unit::receiver.listener<Unit>(this)
    }

}

@EventHandler fun Unit.receiver() = Unit