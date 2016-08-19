package su.jfdev.anci.service

import su.jfdev.anci.util.stream.*
import java.nio.file.*
import java.nio.file.Files.*
import java.util.stream.*
import java.util.stream.Collectors.*

fun Stream<Path>.services(): Map<String, Set<Class<*>>> = filter { isRegularFile(it) }
        .map(Path::toService)
        .overrideAssociate()

private fun Path.toService() = fileName.toString() to implementations()

private fun Path.implementations(): Set<Class<*>> = lines(this)
        .map(::classOrNull)
        .filterNotNull()
        .collect(toSet<Class<*>>())

private fun classOrNull(className: String): Class<*>? = try {
    Class.forName(className)
} catch (e: Throwable) {
    null
}