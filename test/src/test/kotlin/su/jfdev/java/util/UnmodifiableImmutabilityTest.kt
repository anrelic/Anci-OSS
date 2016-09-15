package su.jfdev.java.util

import io.kotlintest.specs.*
import su.jfdev.test.immutability.SpecAdapter.Companion.specAdapter
import su.jfdev.test.immutability.util.*
import java.util.*
import java.util.Collections.*

class UnmodifiableImmutabilityTest: FreeSpec() {
    val adapter = specAdapter

    init {
        "unmodifiableMap()" - {
            val immutability = MockImmutabilities.uuidMap<Any>()
            "when empty" - {
                immutability(adapter) {
                    unmodifiableMap(emptyMap())
                }
            }
            "when nonEmpty" - {
                immutability(adapter) {
                    unmodifiableMap(mapOf(UUID.randomUUID() to ""))
                }
            }
        }
        "unmodifiableList()" - {
            val immutability = MockImmutabilities.list<Any>()
            "when empty" - {
                immutability(adapter) {
                    unmodifiableList(emptyList())
                }
            }
            "when nonEmpty" - {
                immutability(adapter) {
                    unmodifiableList(listOf("one", "two"))
                }
            }
        }
        "unmodifiableSet()" - {
            val immutability = MockImmutabilities.set<Any>()
            "when empty" - {
                immutability(adapter) {
                    unmodifiableSet(emptySet())
                }
            }
            "when nonEmpty" - {
                immutability(adapter) {
                    unmodifiableSet(setOf("one", "two"))
                }
            }
        }
        "unmodifiableCollection()" - {
            val immutability = MockImmutabilities.collection<Any>()
            "when empty" - {
                immutability(adapter) {
                    unmodifiableCollection(emptySet())
                }
            }
            "when nonEmpty" - {
                immutability(adapter) {
                    unmodifiableCollection(setOf("one", "two"))
                }
            }
        }
    }
}