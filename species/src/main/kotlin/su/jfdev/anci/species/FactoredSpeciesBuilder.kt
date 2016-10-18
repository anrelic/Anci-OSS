package su.jfdev.anci.species

import java.util.*

fun <T: Any> SpeciesBuilder<T>.factored(factory: (UUID) -> T) = FactoredSpeciesBuilder(this, factory)
class FactoredSpeciesBuilder<T: Any>(val builder: SpeciesBuilder<T>, val factory: (UUID) -> T) {
    operator fun get(uuid: UUID): T = builder.invoke(uuid) {
        factory(uuid)
    }

    operator fun get(uuid: String): T = get(UUID.fromString(uuid))
}
