package su.jfdev.anci.registry

import com.nhaarman.mockito_kotlin.*
import java.util.*
import java.util.UUID.*

class Sample(override val uuid: UUID, override val registry: Registry<Sample>): Registration<Sample> {
    constructor(): this(randomUUID(), mock())

    override var inRegistry: Boolean = true
}