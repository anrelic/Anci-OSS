package su.jfdev.test.calling

import java.util.*
import kotlin.test.*

class Inspection(private val session: UUID) {
    fun call(): Nothing = throw InspectionThrowable(session)

    infix fun inspect(throwable: Throwable?) = when {
        throwable == null                 -> false
        throwable !is InspectionThrowable -> fail("Process throws $throwable")
        throwable.session != session      -> fail("Process call unknown inspection")
        else                              -> true
    }

    private class InspectionThrowable(val session: UUID): Throwable()
}