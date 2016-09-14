package su.jfdev.test.immutability.util

import com.nhaarman.mockito_kotlin.*
import java.util.*

object MockImmutabilities {
    inline fun <K, reified V: Any> entry() = Immutabilities.entry<K, V>(mockGen())
    inline fun <reified T: Any> set() = Immutabilities.set<T>(mockGen())
    inline fun <reified T: Any> collection() = Immutabilities.collection<T>(mockGen())
    inline fun <reified T: Any> list() = Immutabilities.list<T>(mockGen())
    inline fun <reified K: Any, reified V: Any> map() = Immutabilities.map<K, V>(mockGen(), mockGen())
    inline fun <K: Any, reified V: Any> map(noinline key: () -> K) = Immutabilities.map<K, V>(key, mockGen())
    inline fun <reified V: Any> uuidMap() = Immutabilities.map<UUID, V>(UUID::randomUUID, mockGen())

    inline fun <reified T: Any> mockGen(): () -> T = { mock<T>() }
}