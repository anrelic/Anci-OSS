@file:Suppress("NOTHING_TO_INLINE")

package su.jfdev.anci.util

import java.io.*
import java.net.*
import java.nio.file.*
import java.util.stream.*

fun URL.buffered(): BufferedReader {
    val connection = openConnection()
    val stream = if (connection is JarURLConnection) connection.jarFile.getInputStream(connection.jarEntry)
    else openStream()
    return stream!!.reader().buffered()
}

operator fun URL.contains(line: String): Boolean = buffered().use {
    it.lines().anyMatch {
        it == line
    }
}

inline fun Path.walk(): Stream<Path> = Files.walk(this)
fun Path.walkOrEmpty(): Stream<Path> = if(Files.exists(this)) walk() else Stream.empty()