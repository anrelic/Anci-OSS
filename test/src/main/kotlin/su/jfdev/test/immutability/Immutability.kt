package su.jfdev.test.immutability

interface Immutability<in T> {
    val name: String
    operator fun invoke(adapter: SpecAdapter, checked: () -> T)
    infix fun rename(name: String): Immutability<T>

    data class Suite<in T>(override val name: String,
                           val sub: Set<Immutability<T>>): Immutability<T> {
        override fun invoke(adapter: SpecAdapter, checked: () -> T) = adapter.suite(name) {
            for (immutability in sub) immutability(adapter, checked)
        }

        override fun rename(name: String) = copy(name)
    }

    data class Test<in T>(override val name: String,
                          val validator: (T) -> Unit): Immutability<T> {
        override fun invoke(adapter: SpecAdapter, checked: () -> T) {
            adapter.case(name) {
                validator(checked())
            }
        }

        override fun rename(name: String) = copy(name)
    }

    data class Transform<To, From>(override val name: String,
                                   val transform: (From) -> To,
                                   val immutability: Immutability<To>): Immutability<From> {
        override fun invoke(adapter: SpecAdapter, checked: () -> From) = immutability.rename(name)(adapter) {
            transform(checked())
        }


        override fun rename(name: String) = copy(name)
    }
}

