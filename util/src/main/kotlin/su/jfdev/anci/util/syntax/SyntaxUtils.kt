package su.jfdev.anci.util.syntax

infix fun <T> T.finally(block: (T) -> Unit) = apply(block)