package su.jfdev.anci.service

import io.kotlintest.specs.*

abstract class ServiceLoaderSpec<T: Any>(val clazz: Class<T>, val loader: ServiceLoader): FreeSpec() {
    init {
        "should return provider by type of service" {
            val service = loader[clazz]
            assert(clazz.isInstance(service))
        }
        "when servicing by Class way" - {
            "valid class" {
                val count = loader[VALID].count()
                count shouldBe 1
            }
            "invalid class" {
                val count = loader[INVALID].count()
                count shouldBe 0
            }
            "when clazz is incompatible" - {
                "should skip him and try next" {
                    loader[Partially::class.java].count() shouldBe 1
                }
            }
        }
        "when servicing by String way" - {
            "valid class name" {
                val count = loader[VALID.canonicalName].count()
                count shouldBe 1
            }
            "invalid class name" {
                val count = loader[INVALID.canonicalName].count()
                count shouldBe 1
            }
        }
    }
    val INVALID = Fail::class.java
    val VALID = Successful::class.java
}

interface Successful
interface Fail

object AnyImplementation: Successful, Partially

interface Partially

object FailPart