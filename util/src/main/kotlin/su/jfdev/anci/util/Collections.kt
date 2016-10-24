package su.jfdev.anci.util

import java.util.*
import java.util.Collections.*

/**
 * Jamefrus and his team on 27.06.2016.
 */

fun <T> MutableList<T>.unmodifiable(): List<T> = unmodifiableList(this)

fun <K, V> MutableMap<K, V>.unmodifiable(): Map<K, V> = unmodifiableMap(this)

fun <T> MutableList<T>.synchronized(): MutableList<T> = synchronizedList<T>(this)
fun <T> SynchronizedArrayList(): MutableList<T> = ArrayList<T>().synchronized()
