package su.jfdev.anci.event.phase

interface Event<E> where E: Enum<E> {
    val phase: E
}