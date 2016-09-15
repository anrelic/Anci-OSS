package su.jfdev.test.features

import io.kotlintest.*
import java.util.concurrent.TimeUnit.*

class Repeatable(val interval: Duration, val executable: () -> Unit) {
    infix fun repeat(iterations: Int) {
        var first: Throwable = IllegalStateException("iterations should be positive")
        repeat(iterations) {
            try {
                executable()
                return
            } catch (e: Throwable) {
                first = e
                val (amount, unit) = interval
                unit.sleep(amount)
            }
        }
        throw first
    }

    companion object {
        fun seconds(seconds: Long, executable: () -> Unit) = Repeatable(interval = Duration(1, SECONDS),
                                                                        executable = executable)

        fun millis(seconds: Long, executable: () -> Unit) = Repeatable(interval = Duration(1, MILLISECONDS),
                                                                       executable = executable)

        fun nanos(seconds: Long, executable: () -> Unit) = Repeatable(interval = Duration(1, NANOSECONDS),
                                                                      executable = executable)
    }
}