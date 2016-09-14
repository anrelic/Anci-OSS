package su.jfdev.anci.service

import org.junit.*
import su.jfdev.test.fluent.*
import kotlin.test.*

class ResourceUtilTest {
    val jClass = Any::class.java
    val impl = AnyImplementation::class.java

    @Test fun `META-INF should contains services dir`() = assertHasResource(DIR)

    @Test fun `META-INF - services should contains class-named file`()
            = assertHasResource("$DIR/${jClass.canonicalName}")

    private fun assertHasResource(path: String) {
        val classLoader = jClass.classLoader ?: ClassLoader.getSystemClassLoader()
        val resources = classLoader.getResources(path)
        assert(resources.hasMoreElements())
    }

    @Test fun `resources(dir) return file with #clazz_name`() {
        val hasClass = jClass.resources(DIR).anyMatch {
            it.fileName.toString() == jClass.canonicalName
        }
        assert(hasClass)
    }

    @Test fun `Stream_services() return map service-implementations`() {
        val map = jClass.resources(DIR).services()
        assertFalse("Empty map") {
            map.isEmpty()
        }

        val value = map[jClass.canonicalName] ?: fail("Map doesn't contain class")
        value.single() shouldEqual impl
    }

    companion object {
        const val DIR = "META-INF/services"
    }
}