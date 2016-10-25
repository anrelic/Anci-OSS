package su.jfdev.anci.registry

import io.kotlintest.specs.*
import su.jfdev.test.immutability.SpecAdapter.Companion.specAdapter
import su.jfdev.test.immutability.util.*
import java.util.*
import java.util.concurrent.*

class DelegateRegistryTest: FreeSpec() {
    init {
        test("ConcurrentHashMap") { ConcurrentHashMap() }
        test("TreeMap") { TreeMap() }
        test("HashMap") { HashMap() }
        test("NonEmpty") {
            generateSequence { Sample() }
                    .take(25)
                    .associateBy { it.uuid }
        }
    }

    inline fun test(type: String, crossinline block: () -> Map<UUID, Sample>) = Immutabilities
            .registry(::Sample)
            .rename("$type should be immutable")
            .invoke(specAdapter) {
                DelegateRegistry(delegate = block())
            }

}