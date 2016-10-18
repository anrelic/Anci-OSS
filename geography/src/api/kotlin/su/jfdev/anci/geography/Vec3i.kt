package su.jfdev.anci.geography

data class Vec3i private constructor(val x: Int, val y: Int, val z: Int) {
    infix operator fun minus(side: Int) = modifyAll { it - side }
    infix operator fun plus(side: Int) = modifyAll { it + side }
    infix operator fun times(side: Int) = modifyAll { it * side }
    infix operator fun div(side: Int) = modifyAll { it / side }
    infix operator fun mod(side: Int) = modifyAll { it % side }
    fun move(x: Int, y: Int, z: Int) = Geo[this.x + x, this.y + y, this.z + z]

    companion object {
        @JvmField
        val ZERO = Geo[0, 0, 0]

        fun avoidCache(x: Int, y: Int, z: Int) = Vec3i(x, y, z)

        @JvmName("at") @JvmStatic
        operator fun get(x: Int, y: Int, z: Int) = Geo[x, y, z]
    }

    private inline fun modifyAll(modifier: (Int) -> Int) = Geo[modifier(x), modifier(y), modifier(z)]
}