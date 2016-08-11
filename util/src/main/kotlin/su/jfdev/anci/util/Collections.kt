package su.jfdev.anci.util

import java.util.*
import java.util.Collections.*

/**
 * Jamefrus and his team on 27.06.2016.
 */

fun <T> MutableList<T>.immutable(): List<T> = unmodifiableList(this)
fun <K, V> MutableMap<K, V>.immutable(): Map<K, V> = unmodifiableMap(this)

fun <T> MutableList<T>.sync(): MutableList<T> = synchronizedList<T>(this)

fun <T> syncList(): MutableList<T> = ArrayList<T>().sync()
