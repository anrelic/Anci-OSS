package su.jfdev.codegen

import java.io.*

fun file(path: String) = File(path).orNull()
fun file(parent: File, path: String) = File(parent, path).orNull()
fun file(parent: String, path: String) = File(parent, path).orNull()

fun File.orNull() = let {
    if (exists()) it else null
}