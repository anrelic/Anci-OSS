package bus

import su.jfdev.anci.event.*
import su.jfdev.anci.event.stream.*
import java.util.concurrent.*

class SimpleEventBusSpec: EventBusSpec(bus = SimpleEventBus(StreamEventLoop, CopyOnWriteArraySet()))