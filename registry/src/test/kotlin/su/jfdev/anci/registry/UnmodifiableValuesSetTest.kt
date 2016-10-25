package su.jfdev.anci.registry

import io.kotlintest.properties.*
import io.kotlintest.specs.*
import su.jfdev.test.immutability.SpecAdapter.Companion.specAdapter
import su.jfdev.test.immutability.util.*
import java.util.UUID.*

class UnmodifiableValuesSetTest: FreeSpec() {
    val gen = Gen.string()
    val adapter = specAdapter
    val immutability = Immutabilities.set {
        gen.generate()
    }

    init {
        check() {
            listOf("first", "repeat", "repeat")
        }
        check() {
            val repeat = gen.generate()
            hashMapOf(randomUUID() to "first",
                      randomUUID() to repeat,
                      randomUUID() to repeat).values
        }
    }

    private fun check(from: () -> Collection<String>) {
        val original = from()
        val set = UnmodifiableValuesSet(original)
        "when delegate without guarantee to distinct using [${original.javaClass.name}]" - {
            immutability(adapter) { set }
            "iterator should not repeat entries" {
                set.iterator().asSequence().count() shouldBe 2
            }
            "should have size, that not repeated" {
                set.size shouldBe 2
            }
        }
    }
}