package su.jfdev.test.history

import io.kotlintest.*
import su.jfdev.test.features.*
import java.util.concurrent.TimeUnit.*

class EventCatcher<T>(val history: EventHistory<T>) {
    var delay: Duration = Duration(0, NANOSECONDS)
    var iterations: Int = 1
    var before: () -> Unit = {}

    infix fun <R: Any> catching(action: (T) -> R): R {
        var result: R? = null
        catch {
            result = action(it)
        }
        return result!!
    }

    infix fun successfully(action: (T) -> Unit) = try {
        catch(action); true
    } catch (e: Throwable) {
        false
    }

    infix fun catch(action: (T) -> Unit) = apply {
        before()
        Cyclic.nanos(delay.nanoseconds) {
            single(action)
        } repeat iterations
    }

    infix fun single(action: (T) -> Unit) = apply {
        history.iterator().doCatch(action)
    }

    private fun Iterator<T>.doCatch(action: (T) -> Unit) = when {
        !hasNext()        -> error("None events")
        failCatch(action) -> error("None successfully catch events")
        else              -> Unit
    }

    private fun Iterator<T>.failCatch(action: (T) -> Unit): Boolean {
        for (event in this) try {
            action(event)
            return false
        } catch (e: Throwable) {
        }
        return true
    }
}