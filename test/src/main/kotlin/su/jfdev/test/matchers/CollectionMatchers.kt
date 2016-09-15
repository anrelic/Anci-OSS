@file:JvmMultifileClass
@file:JvmName("Matchers")
@file:Suppress("NOTHING_TO_INLINE")
package su.jfdev.test.matchers

import io.kotlintest.matchers.*

inline infix fun <T> ContainWrapper<out Collection<T>>.only(elements: Array<T>) = only(elements.toList())
inline infix fun <T> ContainWrapper<out Collection<T>>.only(elements: Iterable<T>) = only(elements.toList())
inline infix fun <T> ContainWrapper<out Collection<T>>.only(elements: Collection<T>) = matching {
    value should have size elements.size
    value should contain all elements
}

inline infix fun <T> ContainWrapper<out Collection<T>>.all(elements: Array<T>) = matching {
    for (element in elements) element(element)
}

inline infix fun <T> ContainWrapper<out Collection<T>>.all(elements: Iterable<T>) = matching {
    for (element in elements) element(element)
}


inline infix fun <T> ContainWrapper<out Collection<T>>.one(elements: Array<T>) = matching {
    any(elements) should be equal 1
}

inline infix fun <T> ContainWrapper<out Collection<T>>.any(elements: Iterable<T>) = matching {
    val validCount = elements.count { it in value }
    validCount should be gt 0
    validCount
}

inline infix fun <T> ContainWrapper<out Collection<T>>.any(elements: Array<T>) = any(elements.toList())

inline infix fun <T> ContainWrapper<out Collection<T>>.ibe(elements: Iterable<T>) = matching {
    val validCount = elements.count { it in value }
    validCount should be gt 0
    validCount
}