package handler

import io.kotlintest.matchers.*
import io.kotlintest.specs.*
import su.jfdev.anci.event.handler.*
import su.jfdev.test.matchers.*
import java.util.*

class ObjectHandleSpec: FreeSpec() {
    init {
        "should find all listeners by EventHandler" - {
            val list = ArrayList<Any>()
            val listeners = listeners<MutableList<*>>(this)
            "should execute all handler methods" {
                for (listener in listeners) listener(list)
                list should contain only listOf("first", "other", "last")
            }
        }
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