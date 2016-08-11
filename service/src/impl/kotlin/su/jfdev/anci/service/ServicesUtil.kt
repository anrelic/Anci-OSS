package su.jfdev.anci.service

import su.jfdev.anci.util.stream.*
import java.nio.file.*
import java.nio.file.Files.*
import java.util.stream.Collectors.*

fun java.util.stream.Stream<Path>.services(): Map<Class<*>, Set<Class<*>>> = map(Path::toService)
        .filterNotNull()
        .overrideAssociate()

private fun Path.toService(): Pair<Class<*>, Set<Class<*>>>? {
    val serviceName = fileName.toString()
    val service = classOrNull(serviceName) ?: return null

    return service to implementations()
}

private fun Path.implementations(): Set<Class<*>> = lines(this)
        .map(::classOrNull)
        .filterNotNull()
        .collect(toSet<Class<*>>())

private fun classOrNull(className: String): Class<*>? = try {
    Class.forName(className)
} catch (e: Throwable) {
    null
}