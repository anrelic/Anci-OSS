package su.jfdev.test.immutability.util

import su.jfdev.test.immutability.*
import java.util.AbstractMap.*
import kotlin.collections.Map.*
import kotlin.collections.MutableMap.*

object Immutabilities {
    val iterator = SuiteBuilder.build<Iterator<*>>("iterator") {
        cannot("remove next element") {
            (it as MutableIterator<*>).remove()
        }
    }

    fun <T> listIterator(value: () -> T) = SuiteBuilder.build<ListIterator<T>>("list iterator") {
        append(iterator)
        cannot("add element") {
            (it as MutableListIterator<T>).add(value())
        }
        cannot("set element") {
            (it as MutableListIterator<T>).set(value())
        }
    }

    val iterable = SuiteBuilder.build<Iterable<*>>("iterable") {
        cannot(iterator, "iterator") {
            (this as MutableIterable<*>).iterator()
        }
    }

    fun <K, V> entry(value: () -> V) = SuiteBuilder.build<Entry<K, V>>("entry") {
        cannot("set any value") {
            (it as MutableEntry<K, V>).setValue(value())
        }
    }

    fun <T> collection(value: () -> T) = SuiteBuilder.build<Collection<T>>("collection") {
        by("iterable") { iterable }
        cannot("add any value") {
            (it as MutableCollection<T>).add(value())
        }
        cannot("remove any value") {
            (it as MutableCollection<T>).remove(value())
        }
        cannot("add any values") {
            (it as MutableCollection<T>).addAll(elements = listOf(value()))
        }
        cannot("remove any values") {
            (it as MutableCollection<T>).removeAll(elements = listOf(value()))
        }
        cannot("retain values") {
            (it as MutableCollection<T>).retainAll(elements = listOf(value()))
        }
        cannot("clear values") {
            (it as MutableCollection<T>).clear()
        }
    }

    fun <T> set(value: () -> T) = SuiteBuilder.build<Set<T>>("set") {
        by("collection") {
            collection(value)
        }
    }


    fun <T> sublist(value: () -> T) = SuiteBuilder.build<List<T>>("sublist") {
        by("collection") {
            collection(value)
        }
        cannot(listIterator(value), "listIterator") {
            listIterator()
        }
        cannot(listIterator(value), "listIterator(index)") {
            listIterator(0)
        }
        cannot("set any value at index") {
            (it as MutableList<T>).add(0, value())
        }
        cannot("add any value at index") {
            (it as MutableList<T>).add(0, value())
        }
        cannot("remove any value at index") {
            (it as MutableList<T>).removeAt(0)
        }
        cannot("add any values at index") {
            (it as MutableList<T>).addAll(index = 0, elements = listOf(value()))
        }
    }

    fun <T> list(value: () -> T) = SuiteBuilder.build<List<T>>("list") {
        by("list") {
            sublist(value)
        }
        cannot(sublist(value), "sublist") {
            when (size) {
                0    -> this
                1    -> subList(0, 1)
                else -> subList(0, size - 1)
            }
        }
    }

    fun <K, V> map(key: () -> K, value: () -> V) = SuiteBuilder.build<Map<K, V>>("map entries") {
        val genEntry: () -> Entry<K, V> = {
            SimpleEntry(key(), value())
        }
        cannot(set(key), "keys") { keys }
        cannot(collection(value), "values") { values }
        cannot(set(genEntry), "entries") { entries }
        cannot("clear values") {
            (it as MutableMap<K, V>).clear()
        }
        cannot("contains mutable entries") {
            val toSet = value()
            if (it.entries.isEmpty()) throw Exception("Stub, because none entries to checking")
            for (entry in it.entries) {
                (entry as MutableEntry<K, V>).setValue(toSet)
            }
        }
        cannot("put entry") {
            (it as MutableMap<K, V>).put(key(), value())
        }
        cannot("remove key") {
            (it as MutableMap<K, V>).remove(key())
        }
        cannot("put entries") {
            val map = mapOf(key() to value())
            (it as MutableMap<K, V>).putAll(map)
        }
    }


}

