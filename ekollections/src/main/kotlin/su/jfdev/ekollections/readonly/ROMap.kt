package su.jfdev.ekollections.readonly

import kotlin.collections.Map.*

class ROMap<K, out V>(override val delegate: Map<K, V>): ReadOnly(), Map<K, V> by delegate {
    override val entries: Set<Entry<K, V>> by lazy {
        delegate.entries.readOnly()
    }
    override val keys: Set<K> by lazy {
        delegate.keys.readOnly()
    }
    override val values: Collection<V> by lazy {
        delegate.values.readOnly()
    }
}

