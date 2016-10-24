package su.jfdev.anci.util

inline fun <reified E: Exception> customCatch(block: () -> Unit, handler: (E) -> Unit): E? {
    try {
        block()
        return null
    } catch (e: Exception){
        if(e !is E) throw e
        handler(e)
        return e
    }
}

inline fun <R> orNull(block: () -> R): R? = try {
    block()
} catch (e: Throwable) {
    null
}