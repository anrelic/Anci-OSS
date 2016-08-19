package su.jfdev.anci.service

import kotlinx.support.jdk8.collections.*
import su.jfdev.anci.util.*
import su.jfdev.anci.util.stream.*
import java.io.*
import java.nio.file.*
import java.nio.file.Files.*
import java.util.stream.*

val ServiceSource.paths: Stream<Path> get() = (internalPaths + externalPaths).filter { isRegularFile(it) }

private val ServiceSource.internalPaths: Stream<Path> get() = internal.stream().flatMap {
    ServiceLoader::class.java.resources(it)
}

private val ServiceSource.externalPaths: Stream<Path> get() = external.stream().flatMap {
    File(it).toPath().walkOrEmpty()
}