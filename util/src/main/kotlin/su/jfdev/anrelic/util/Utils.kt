@file:Suppress("unused")

package su.jfdev.anrelic.util

inline fun <reified T> Any?.onlyAs(action: (T) -> Unit) {
    if(this != null && this is T) return action(this)
}