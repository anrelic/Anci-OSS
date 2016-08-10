package handler

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.*
import su.jfdev.anrelic.event.handler.*
import java.util.*

class ObjectHandleSpec {
    @Test fun `should execute all handler methods`() {
        val list = ArrayList<Any>()
        for (listener in listeners<MutableList<*>>(this))
            listener(list)
        Assertions.assertThat(list).containsOnly("first", "other", "last")

    }

    @EventHandler fun MutableList<Any>.`add first`() {
        add("first")
    }

    @EventHandler fun MutableList<Any>.`add other`() {
        add("other")
    }

    @EventHandler fun MutableList<Any>.`add last`() {
        add("last")
    }
}