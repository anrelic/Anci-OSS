package su.jfdev.anci.util

import kotlin.reflect.*

fun <T> SuppliedThreadLocal(supplier: () -> T) = ThreadLocal(supplier)
inline fun <T> ThreadLocal(crossinline lazy: () -> T) = object: ThreadLocal<T>() {
    override fun initialValue() = lazy()
}

operator fun <T> ThreadLocal<T>.getValue(owner: Any?, property: KProperty<*>): T = get()
operator fun <T> ThreadLocal<T>.setValue(owner: Any?, property: KProperty<*>, new: T) = set(new)
