package su.jfdev.test.calling

data class Inspector(val predicate: (Int) -> Boolean, val limit: Int = 1000) {
    constructor(predicate: (Int) -> Boolean): this(predicate, 1000)

    inline fun repeat(action: (Boolean) -> Unit) = repeat(limit) {
        action(predicate(it))
    }

    operator fun not() = copy(predicate = { !predicate(it) })

    operator fun <R> invoke(init: (Inspection) -> R) = CallingInspection(this, init)

    companion object Inspectors {
        val always = Inspector { true }
        val never = Inspector { false }

        val firstOnly = Inspector { it == 0 }
        val fromSecond = Inspector { it != 0 }

        infix fun after(times: Int) = Inspector { it !in 0..times }
        infix fun before(times: Int) = Inspector { it in 0..times }
    }
}