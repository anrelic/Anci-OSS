package bus

import su.jfdev.anci.event.EventService.Companion.createBus
import su.jfdev.anci.event.EventService.Companion.createLoop

class SimpleEventBusSpec: EventBusSpec(bus = createBus(eventLoop = createLoop()))