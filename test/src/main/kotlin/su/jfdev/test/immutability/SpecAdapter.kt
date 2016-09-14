package su.jfdev.test.immutability

import io.kotlintest.specs.*

interface SpecAdapter {
    fun suite(name: String, block: () -> Unit)
    fun case(name: String, block: () -> Unit)

    object Empty: SpecAdapter {
        override fun suite(name: String, block: () -> Unit) = block()
        override fun case(name: String, block: () -> Unit) = block()
    }

    companion object {
        val FreeSpec.specAdapter: SpecAdapter get() = object: SpecAdapter {
            override fun suite(name: String, block: () -> Unit) = name - block

            override fun case(name: String, block: () -> Unit) {
                name(block)
            }
        }
    }
}