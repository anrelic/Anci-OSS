package su.jfdev.anci.geography

import io.kotlintest.matchers.*
import io.kotlintest.specs.*

/**
 * Jamefrus and his team on 26.06.2016.
 */
abstract class CoverageSpec<out T>(val included: Collection<T>,
                                   val excluded: Collection<T>,
                                   val coverage: Collection<T>): FreeSpec() {
    init {
        "should validate location" - {
            "for including if it's included" {
                forAll(included) {
                    coverage should contain element it
                }
            }
            "for excluding if it isn't included" {
                forNone(excluded) {
                    coverage should contain element it
                }
            }
        }
        "should be empty with 0 size" - {
            if (coverage.isEmpty()) {
                coverage should have size 0
            }
        }
    }
}