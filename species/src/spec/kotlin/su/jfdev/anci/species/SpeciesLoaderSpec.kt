package su.jfdev.anci.species

import io.kotlintest.specs.*
import java.util.*
import kotlin.collections.Map.*

open class SpeciesLoaderSpec(val service: SpeciesLoader): FreeSpec() {
    init {
        "should load all species" - {
            fun species(species: StringSpecies) = row(species)
            val table = table(headers("object"),
                              species(FirstStringSpecies),
                              species(SecondStringSpecies))
            "by custom name" {
                val species = service[String::class.java, "testSpecies"]
                forAll(table) {
                    forAll(it.map.entries) {
                        val (uuid, item) = it
                        species[uuid] shouldBe item
                    }
                }
            }
            "by class name" {
                val species = service[String::class.java]
                forAll(table) {
                    forAll(it.map.entries) {
                        val (uuid, item) = it
                        species[uuid] shouldBe item
                    }
                }
            }
        }
    }

    object FirstStringSpecies: StringSpecies()
    object SecondStringSpecies: StringSpecies()

    open class StringSpecies: Species<String> {
        internal val first = UUID.randomUUID() to "first"
        internal val second = UUID.randomUUID() to "second"
        internal val third = UUID.randomUUID() to "third"

        val map: Map<UUID, String> = mapOf(first, second, third)

        override fun get(uuid: UUID) = map[uuid]
        override fun iterator(): Iterator<Entry<UUID, String>> = map.iterator()
    }
}

