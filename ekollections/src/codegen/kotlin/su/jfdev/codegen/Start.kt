package su.jfdev.codegen

import java.io.*
import java.nio.file.*

fun main(args: Array<String>) {
    Files.walk(Def.input.toPath()).parallel().forEach {
        gen(it.toFile())
    }
}

private fun gen(file: File) {
    if (file.isFile && file.extension == Def.extension) {
        val pack = Pack(file)
        for (type in Def.primitives.keys) CodeGen(pack, type).generate()
    }
}