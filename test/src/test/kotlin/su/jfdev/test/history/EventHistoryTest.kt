package su.jfdev.test.history

import io.kotlintest.matchers.*
import su.jfdev.test.features.*
import su.jfdev.test.matchers.*
import java.util.*

class EventHistoryTest: Stepwise.Spec() {
    init {
        stepwise("listening") {
            val listeners: MutableList<(String) -> Unit> = ArrayList()
            val history = EventHistory<String> {
                listeners += it
            }

            fun fire(text: String) {
                for (listener in listeners) listener(text)
            }
            after("fire two messages") {
                fire("1")
                fire("2")
            }
            test("should catch all") {
                val historyList = Sequence { history.iterator() }.toList()
                historyList should contain all listOf("2", "1")
            }
        }


    }
}