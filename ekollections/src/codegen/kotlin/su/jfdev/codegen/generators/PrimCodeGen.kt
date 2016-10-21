package su.jfdev.codegen.generators

import su.jfdev.codegen.*
import java.io.*

class PrimCodeGen(pack: Pack, type: String): CodeGen(pack, type) {
    init {
        Def.primitives[type]?.let {
            replacements += it
        }
        annotation("""JvmName("${name}Util")""")
        annotation("""Suppress("NOTHING_TO_INLINE")""")
        annotation("""JvmMultifileClass""")
    }

    override fun BufferedWriter.generate() {
        appendln(code)
    }

    val code = run {
        var code = pack.code.readText()
        val properties = code.lineSequence()
                .filter { it.startsWith("//#") }
                .map { it.substringAfter("//#") }
                .associate { toProperty(it) }
        for ((key, value) in replacements + properties) {
            code = code.replace("#$key#", value)
        }
        code
    }
}