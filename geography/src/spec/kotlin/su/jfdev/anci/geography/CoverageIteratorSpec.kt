package su.jfdev.anci.geography

import io.kotlintest.matchers.*
import io.kotlintest.specs.*
import su.jfdev.anci.geography.services.*

abstract class CoverageIteratorSpec<out I, T: Collection<I>>(coverage: T,
                                                             iterator: CoverageIterator<I, T>): FreeSpec() {
    init {
        val collected = iterator.iterator(coverage).asSequence().toList()
        "should iterate all ${coverage.size}" {
            collected should have size coverage.size
        }
        "should include all" {
            forAll(collected) {
                coverage should contain element it
            }
        }
    }
}