package su.jfdev.anrelic.util

import org.apache.logging.log4j.*


inline fun <reified E: Exception> Logger.catchError(message: String, block: () -> Unit): E? = customCatch(block){
    error(message, it)
}

inline fun <reified E: Exception> Logger.catchWarn(message: String, block: () -> Unit): E? = customCatch(block){
    warn(message, it)
}

inline fun Logger.catchAnyWarn(message: String, block: () -> Unit): Exception? = catchWarn(message, block)

inline fun Logger.catchAnyError(message: String, block: () -> Unit): Exception? = catchError(message, block)

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