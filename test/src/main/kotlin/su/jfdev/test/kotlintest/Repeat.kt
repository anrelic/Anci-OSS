package su.jfdev.test.kotlintest

import io.kotlintest.*
import java.util.concurrent.TimeUnit.*

class Repeat(val interval: Duration, val iterations: Int, val executable: () -> Unit) {
    fun invoke() = repeat(iterations) {
        try {
            executable()
            return
        } catch (e: Throwable) {
            val (amount, unit) = interval
            unit.sleep(amount)
        }
    }

    companion object {
        fun second(executable: () -> Unit) = Repeat(interval = Duration(100, MILLISECONDS),
                                                    iterations = 10,
                                                    executable = executable)
    }
}