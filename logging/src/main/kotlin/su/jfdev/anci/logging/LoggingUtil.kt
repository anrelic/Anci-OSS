@file:Suppress("NOTHING_TO_INLINE")

package su.jfdev.anci.logging

import su.jfdev.anci.logging.LogLevel.*

/**
 * As example Logger["foo"] + "bar" similar Logger["foo.bar"]
 */
operator fun Logger.plus(subPath: String) = Logger["$name.$subPath"]

inline infix fun Logger.info(throwable: Throwable) = log(INFO, throwable)
inline infix fun Logger.warn(throwable: Throwable) = log(WARN, throwable)
inline infix fun Logger.error(throwable: Throwable) = log(ERROR, throwable)
inline infix fun Logger.trace(throwable: Throwable) = log(TRACE, throwable)
inline infix fun Logger.debug(throwable: Throwable) = log(DEBUG, throwable)


inline fun Logger.info(throwable: Throwable, message: () -> String) = log(INFO, throwable, message)
inline fun Logger.warn(throwable: Throwable, message: () -> String) = log(WARN, throwable, message)
inline fun Logger.error(throwable: Throwable, message: () -> String) = log(ERROR, throwable, message)
inline fun Logger.trace(throwable: Throwable, message: () -> String) = log(TRACE, throwable, message)
inline fun Logger.debug(throwable: Throwable, message: () -> String) = log(DEBUG, throwable, message)

inline infix fun Logger.info(message: () -> String) = log(INFO, message)
inline infix fun Logger.warn(message: () -> String) = log(WARN, message)
inline infix fun Logger.error(message: () -> String) = log(ERROR, message)
inline infix fun Logger.trace(message: () -> String) = log(TRACE, message)
inline infix fun Logger.debug(message: () -> String) = log(DEBUG, message)

inline fun Logger.log(level: LogLevel, throwable: Throwable) {
    if (level in this) print(level, throwable)
}

inline fun Logger.log(level: LogLevel, throwable: Throwable, message: () -> String) {
    if (level in this) print(level, message(), throwable)
}

inline fun Logger.log(level: LogLevel, message: () -> String) {
    if (level in this) print(level, message())
}