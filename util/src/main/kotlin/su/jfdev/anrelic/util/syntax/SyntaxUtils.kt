package su.jfdev.anrelic.util.syntax

infix fun <T> T.finally(block: (T) -> Unit) = apply(block)