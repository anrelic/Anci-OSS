package su.jfdev.codegen

import java.io.*

class Pack(val code: File) {
    val name = code.nameWithoutExtension

    val set = (code.parentFile - Def.input).path.substringBefore("/").substringBefore("\\")
    val pack = code.parentFile - File(Def.input, set)
    val output = Def.output + set + "codegen" + pack.path


    init {
        println("set = $set")
        println("output = $output")
        println("pack = $pack")
    }

    private val _package = pack.path.replace('/', '.').replace('\\', '.')
    override fun toString() = _package

    private operator fun File.plus(name: String) = File(this, name)
    private operator fun File.minus(file: File) = relativeTo(file)
}