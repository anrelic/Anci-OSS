package su.jfdev.anrelic.util.stream

import java.util.function.*
import java.util.function.Function
import java.util.stream.*
import java.util.stream.Collectors.*

fun <K, V> Stream<Pair<K, V>>.associate(): Map<K, V> = collect(PairCollectors.collector())

fun <K, V> Stream<Pair<K, V>>.overrideAssociate(): Map<K, V> = collect(PairCollectors.override())

@Suppress("UNCHECKED_CAST")
object PairCollectors {
    private val keyExtractor: Function<Pair<*, *>, Any> = Function { it.first }
    private val valueExtractor: Function<Pair<*, *>, Any> = Function { it.second }

    private val collector: Collector<*, *, *> = toMap(keyExtractor, valueExtractor)

    private val overrideMerger: BinaryOperator<Any> = BinaryOperator { first, second -> second }
    private val overrideCollector: Collector<*, *, *> = toMap(keyExtractor, valueExtractor, overrideMerger)

    fun <K, V> key() = keyExtractor as Function<Pair<K, V>, K>
    fun <K, V> value() = valueExtractor as Function<Pair<K, V>, V>
    fun <K, V> collector() = collector as Collector<Pair<K, V>, *, MutableMap<K, V>>

    fun <K, V> override() = overrideCollector as Collector<Pair<K, V>, *, MutableMap<K, V>>

    fun <K, V> merging(merger: (V, V) -> V): Collector<Pair<K, V>, *, MutableMap<K, V>> = toMap(
            key<K, V>(),
            value<K, V>(),
            BinaryOperator(merger))
}