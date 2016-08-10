package loop

import su.jfdev.anrelic.event.*
import su.jfdev.anrelic.event.stream.*

class StreamEventLoopSpec: EventLoopSpec() {
    override val eventLoop: EventLoop = StreamEventLoop
}