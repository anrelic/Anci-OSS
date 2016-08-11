package su.jfdev.anci.species

import java.util.*

open class MapWrapper<out T>(delegate: Map<UUID, T>): Map<UUID, T> by delegate