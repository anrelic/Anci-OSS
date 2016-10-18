package su.jfdev.anci.geography

import io.kotlintest.matchers.*
import io.kotlintest.specs.*
import su.jfdev.anci.geography.services.*

abstract class CoverageIteratorSpec<out I, T: Collection<I>>(coverage: T,
                                                             iterator: CoverageIterator<I, T>): FreeSpec() {
    init {
        val seq = Sequence { iterator.iterator(coverage) }
        "should iterate all ${coverage.size}" {
            seq.count() shouldBe coverage.size
        }
        "should include all" {
            for (vec in seq) {
                coverage should contain element vec
            }
        }
    }
}