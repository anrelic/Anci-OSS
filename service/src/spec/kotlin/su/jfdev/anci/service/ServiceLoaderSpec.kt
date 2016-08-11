package su.jfdev.anci.service

import org.junit.jupiter.api.*
import su.jfdev.util.fluent.*
import java.util.*

abstract class ServiceLoaderSpec<T: Any>(val clazz: Class<T>, collector: ServiceCollector) {
    val INVALID = Fail::class.java
    val VALID = Successful::class.java
    val loader = collector collect ServiceCollector.Default.DEFAULT_SOURCE
    @Test fun `should return provider by type of service`() {
        val service = loader[clazz]
        assert(clazz.isInstance(service))
    }

    @Test fun `should fail when servicing by checked way invalid clazz`() {
        val iterator = loader[INVALID].iterator()
        shouldThrow<NoSuchElementException> {
            iterator.next()
        }
    }

    @Test fun `should not fail when servicing by unchecked way invalid clazz`() {
        val iterator = loader.unchecked(INVALID).iterator()
        iterator.next()
    }

    @Test fun `should not fail when servicing by unchecked way valid clazz`() {
        val iterator = loader.unchecked(VALID).iterator()
        iterator.next()
    }

    @Test fun `should not fail when servicing by checked way valid clazz`() {
        val iterator = loader[VALID].iterator()
        iterator.next()
    }

    @Test fun `should skip when initialization fail (as cast exception)`() {
        loader[Partially::class.java].count() shouldBe 1
    }
}

interface Successful
interface Fail

object AnyImplementation: Successful, Partially

interface Partially

object FailPart