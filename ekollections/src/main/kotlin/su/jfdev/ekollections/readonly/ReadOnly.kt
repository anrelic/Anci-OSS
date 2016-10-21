package su.jfdev.ekollections.readonly

import java.io.*
import java.util.*

abstract class ReadOnly: Serializable {
    protected abstract val delegate: Any
    override fun equals(other: Any?) = delegate == other
    override fun hashCode(): Int = delegate.hashCode()
    override fun toString(): String = "ReadOnly: $delegate"

    private companion object {
        @JvmField val serialVersionUID = 1L
    }
}

fun <K, V> Map<K, V>.readOnly() = readOnly { ROMap(it) }
fun <T> Iterator<T>.readOnly() = readOnly { ROIterator(it) }
fun <T> ListIterator<T>.readOnly() = readOnly { ROListIterator(it) }
fun <T> Iterable<T>.readOnly() = readOnly { ROIterable(it) }
fun <T> Collection<T>.readOnly() = readOnly { ROCollection(it) }
fun <T> Set<T>.readOnly() = readOnly { ROSet(it) }
fun <T> List<T>.readOnly() = when (this) {
    is RORandomList, is ROList -> this
    is RandomAccess            -> RORandomList(this)
    else                       -> ROList(this)
}


private inline fun <F, reified RO: ReadOnly> F.readOnly(factory: (F) -> RO): RO = when (this) {
    is RO -> this
    else  -> factory(this)
}