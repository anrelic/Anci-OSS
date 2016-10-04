package su.jfdev.anci.logging

import su.jfdev.anci.logging.LogLevel.*

infix inline fun <R> Logger.unchecked(block: () -> R): R? = unchecked(ERROR, block)
infix inline fun <R> Logger.exception(block: () -> R): R? = exception(ERROR, block)
infix inline fun <R> Logger.throwable(block: () -> R): R? = throwable(ERROR, block)

inline fun <R> Logger.unchecked(level: LogLevel, block: () -> R): R? = catching<R, RuntimeException>(level, block)
inline fun <R> Logger.exception(level: LogLevel, block: () -> R): R? = catching<R, Exception>(level, block)
inline fun <R> Logger.throwable(level: LogLevel, block: () -> R): R? = catching<R, Throwable>(level, block)

inline fun <R, reified T: Throwable> Logger.catching(level: LogLevel, block: () -> R): R? {
    doOrCatch<T>(level, { return block() }) {
        return null
    }
    error("should exit by doOrCatch")
}

infix inline fun Logger.unchecked(block: () -> Unit): Unit = catch<RuntimeException>(block)
infix inline fun Logger.exception(block: () -> Unit): Unit = catch<Exception>(block)
infix inline fun Logger.throwable(block: () -> Unit): Unit = catch<Throwable>(block)

inline fun Logger.unchecked(level: LogLevel, block: () -> Unit): Unit = catch<RuntimeException>(level, block)
inline fun Logger.exception(level: LogLevel, block: () -> Unit): Unit = catch<Exception>(level, block)
inline fun Logger.throwable(level: LogLevel, block: () -> Unit): Unit = catch<Throwable>(level, block)

infix inline fun <reified T: Throwable> Logger.catch(block: () -> Unit): Unit = catch<T>(ERROR, block)
inline fun <reified T: Throwable> Logger.catch(level: LogLevel, block: () -> Unit): Unit = doOrCatch<T>(level, block) {
}

inline fun <reified T: Throwable> Logger.doOrCatch(level: LogLevel, block: () -> Unit, or: () -> Unit) = tryCatch<T>(block) {
    log(level, it)
    or()
}

inline fun <reified T: Throwable> tryCatch(`try`: () -> Unit, catch: (T) -> Unit) = try {
    `try`()
} catch (e: Throwable) {
    when (e) {
        is T -> catch(e)
        else -> throw e
    }
}