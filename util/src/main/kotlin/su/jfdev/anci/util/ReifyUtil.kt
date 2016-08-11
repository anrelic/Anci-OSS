package su.jfdev.anci.util


inline fun <reified T: Any> reify() = T::class.java