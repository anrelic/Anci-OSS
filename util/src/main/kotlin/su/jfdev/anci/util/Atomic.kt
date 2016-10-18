package su.jfdev.anci.util

import java.util.concurrent.atomic.*

inline fun AtomicLong.update(updater: (Long) -> Long) {
    while (!tryUpdate(updater)) continue
}

inline fun AtomicLong.tryUpdate(updater: (Long) -> Long): Boolean {
    val prev = get()
    val next = updater(prev)
    return compareAndSet(prev, next)
}

inline fun AtomicInteger.update(updater: (Int) -> Int) {
    while (!tryUpdate(updater)) continue
}

inline fun AtomicInteger.tryUpdate(updater: (Int) -> Int): Boolean {
    val prev = get()
    val next = updater(prev)
    return compareAndSet(prev, next)
}

inline fun AtomicBoolean.update(updater: (Boolean) -> Boolean) {
    while (!tryUpdate(updater)) continue
}

inline fun AtomicBoolean.tryUpdate(updater: (Boolean) -> Boolean): Boolean {
    val prev = get()
    val next = updater(prev)
    return compareAndSet(prev, next)
}

inline fun <T> AtomicReference<T>.update(updater: (T) -> T) {
    while (!tryUpdate(updater)) continue
}

inline fun <T> AtomicReference<T>.tryUpdate(updater: (T) -> T): Boolean {
    val prev = get()
    val next = updater(prev)
    return compareAndSet(prev, next)
}