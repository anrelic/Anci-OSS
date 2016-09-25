package su.jfdev.test.history

import io.kotlintest.matchers.*
import su.jfdev.test.features.*
import su.jfdev.test.matchers.*
import java.util.*

class EventHistoryTest: Stepwise.Spec() {
    init {
        stepwise("listening") {
            val listeners: MutableList<(String) -> Unit> = ArrayList()
            take("EventHistory") {
                EventHistory<String> {
                    listeners += it
                }
            } then {
                after("fire two messages") {
                    for (listener in listeners) {
                        listener("1")
                        listener("2")
                    }
                }
                test("should catch all") {
                    result should contain all listOf("2", "1")
                }
            }
        }
    }
}