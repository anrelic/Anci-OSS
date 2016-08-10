package su.jfdev.anrelic.util


inline fun <reified T: Any> reify() = T::class.java