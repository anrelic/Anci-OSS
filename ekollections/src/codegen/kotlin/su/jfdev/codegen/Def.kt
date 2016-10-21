package su.jfdev.codegen

import java.io.*

object Def {
    val extension = "tkt"
    val output = File("C:\\AnciWorkspace\\Anci-OSS\\ekollections\\src")
    val input = File("C:\\AnciWorkspace\\Anci-OSS\\ekollections\\src\\codegen\\resources")
    val primitives = mapOf("int" to mapOf("PRIM" to "Int",
                                          "prim" to "int"),

                           "byte" to mapOf("PRIM" to "Byte",
                                           "prim" to "byte"),

                           "long" to mapOf("PRIM" to "Long",
                                           "prim" to "long"),

                           "bool" to mapOf("PRIM" to "Boolean",
                                           "prim" to "boolean"),

                           "short" to mapOf("PRIM" to "Short",
                                            "prim" to "short"),

                           "double" to mapOf("PRIM" to "Double",
                                             "prim" to "double"),

                           "float" to mapOf("PRIM" to "Float",
                                            "prim" to "float"),

                           "char" to mapOf("PRIM" to "Char",
                                           "prim" to "char"))
}