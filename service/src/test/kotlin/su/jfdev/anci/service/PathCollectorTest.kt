package su.jfdev.anci.service

import io.kotlintest.matchers.*
import io.kotlintest.specs.*
import org.junit.rules.*
import java.io.*
import java.nio.file.*
import java.util.stream.*

class PathCollectorTest: FreeSpec() {
    val folder: TemporaryFolder = TemporaryFolder()
    init {
        "should find resources paths, which" - {
            val paths = ServiceSource(internal = setOf("paths"),
                                      external = emptySet()).paths.toList()
            "should contains only one path" {
                paths should have size 1
            }

            "should successfully create line stream" {
                Files.lines(paths[0]).forEach { }
            }
        }
        "should find external paths, which" - {
            folder.create()
            val externalDir: File = folder.newFolder("paths")
            val external = ServiceSource(internal = emptySet(), external = setOf(externalDir.path))
            "when create directory with single service file" - {
                File(externalDir, "collector").apply {
                    createNewFile()
                    writeText("java.lang.Object")
                }
                "and extract paths with PathCollectorKt.paths" - {
                    val paths = external.paths.toList()
                    "should contains only one path" {
                        paths should have size 1
                    }

                    "should successfully create line stream" {
                        Files.lines(paths[0]).forEach { }
                    }
                }
            }
        }
    }

    override fun afterAll() = folder.delete()
    private fun <T> Stream<T>.toList(): List<T> = collect(Collectors.toList<T>())

}