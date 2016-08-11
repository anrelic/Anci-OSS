package su.jfdev.anci.event

interface EventLoop {
    fun <E: Any> handle(subscribers: Collection<(E) -> Unit>, event: E)
    fun <E: Any> sync(subscribers: Collection<(E) -> Unit>, event: E)
}