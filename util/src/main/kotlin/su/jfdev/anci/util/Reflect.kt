package su.jfdev.anci.util

val Class<*>.clazzLoader: ClassLoader get() = classLoader ?: ClassLoader.getSystemClassLoader()