package loop

import su.jfdev.anci.event.*
import su.jfdev.anci.event.stream.*

class StreamEventLoopSpec: EventLoopSpec() {
    override val eventLoop: EventLoop = StreamEventLoop
}