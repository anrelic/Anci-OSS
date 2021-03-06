@file:Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")

package su.jfdev.anci.service

import kotlinx.support.jdk8.collections.*
import su.jfdev.anci.util.*
import java.net.*
import java.nio.file.*
import java.nio.file.FileSystems.*
import java.util.stream.*
import java.util.List as JavaList


fun Class<*>.resources(dir: String): Stream<Path> = clazzLoader
        .getResources(dir)
        .toList()
        .stream()
        .flatMap { it.load(dir).walk() }

private fun URL.load(dir: String) = toURI().load(dir)

private fun URI.load(dir: String): Path = when (scheme) {
    "jar" -> fileSystem.getPath("/$dir")
    else  -> Paths.get(this)
}

private val URI.fileSystem: FileSystem get() = try {
    getFileSystem(this)
} catch (e: FileSystemNotFoundException) {
    newFileSystem(this, emptyMap<String, Any>())
}

