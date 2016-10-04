package su.jfdev.anci.logging

import su.jfdev.anci.logging.LogLevel.*

inline fun <R> Logger.unchecked(block: () -> R): R? = unchecked(ERROR, block)
inline fun <R> Logger.exception(block: () -> R): R? = exception(ERROR, block)
inline fun <R> Logger.throwable(block: () -> R): R? = throwable(ERROR, block)

inline fun <R> Logger.unchecked(level: LogLevel, block: () -> R): R? = catching<R, RuntimeException>(level, block)
inline fun <R> Logger.exception(level: LogLevel, block: () -> R): R? = catching<R, Exception>(level, block)
inline fun <R> Logger.throwable(level: LogLevel, block: () -> R): R? = catching<R, Throwable>(level, block)

inline fun <reified E: Throwable> Logger.catch(block: () -> Unit) = catching<Unit, E>(ERROR, block)
inline fun <reified E: Throwable> Logger.catch(level: LogLevel, block: () -> Unit) = catching<Unit, E>(level, block)
inline fun <R, reified E: Throwable> Logger.catching(level: LogLevel, block: () -> R): R? = try {
    block()
} catch (e: Throwable) {
    when (e) {
        is E -> log(level, e)
        else -> throw e
    }
    null
}