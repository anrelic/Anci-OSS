package su.jfdev.anrelic.util

val Class<*>.clazzLoader: ClassLoader get() = classLoader ?: ClassLoader.getSystemClassLoader()