package su.jfdev.test.mockito

import com.nhaarman.mockito_kotlin.*
import org.mockito.*

inline fun <reified T: Any> mockBy(settings: MockSettings.() -> Unit) = mock<T>(withSettings().apply(settings))
inline fun <reified As: Any> delegate(delegate: Any): As = mockBy {
    delegate(delegate)
}

fun MockSettings.delegate(delegate: Any): MockSettings = defaultAnswer {
    it.method.invoke(delegate, *it.arguments)
}

