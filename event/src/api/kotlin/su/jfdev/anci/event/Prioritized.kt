package su.jfdev.anci.event


/**
 * Jamefrus and his team on 13.06.2016.
 */
interface Prioritized {

    val priority: Priority

    enum class Priority {
        High,
        Medium,
        Low;

        companion object {
            val DEFAULT: Priority = Priority.Medium
        }
    }
}