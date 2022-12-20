package stdogr.aoc2022

class Day10 : Task<Int>("10_cathode_ray_tube") {

    override fun partOne(input: String): Int {
        val regex = "addx (-?)([0-9]+)".toRegex()

        val cycles = mutableListOf<Pair<Int, Int>>()
        cycles.add(1 to 1)

        input.lines()
            .forEach { line ->
                if (line == "noop") {
                    val lastCycle = cycles.last()
                    cycles.add(lastCycle.first + 1 to lastCycle.second)
                } else {
                    val find = regex.find(line)!!
                    val multiplier = if (find.groups[1]!!.value == "") 1 else -1
                    val value = find.groups[2]!!.value.toInt() * multiplier

                    val lastCycle = cycles.last()
                    cycles.add(lastCycle.first + 1 to lastCycle.second)
                    cycles.add(lastCycle.first + 2 to lastCycle.second + value)
                }
            }

        return cycles
            .filter {
                it.first in listOf(20, 60, 100, 140, 180, 220)
            }
            .sumOf { it.first * it.second }
    }

    override fun partTwo(input: String): Int {
        val regex = "addx (-?)([0-9]+)".toRegex()

        val cycles = mutableListOf<Pair<Int, Int>>()
        cycles.add(1 to 1)

        val pixels = mutableListOf<String>()
        val crtPixelIndex = CrtPixelIndex()

        input.lines()
            .forEach { line ->
                if (line == "noop") {
                    val lastCycle = cycles.last()
                    draw(crtPixelIndex, pixels, lastCycle)
                    cycles.add(lastCycle.first + 1 to lastCycle.second)
                } else {
                    val find = regex.find(line)!!
                    val multiplier = if (find.groups[1]!!.value == "") 1 else -1
                    val value = find.groups[2]!!.value.toInt() * multiplier

                    val lastCycle = cycles.last()
                    draw(crtPixelIndex, pixels, lastCycle)
                    cycles.add(lastCycle.first + 1 to lastCycle.second)
                    draw(crtPixelIndex, pixels, cycles.last())
                    cycles.add(lastCycle.first + 2 to lastCycle.second + value)
                }
            }

        pixels.chunked(40)
            .forEach { println(it.joinToString("")) }

        return 0
    }

    private fun draw(crtPixelIndex: CrtPixelIndex, pixels: MutableList<String>, lastCycle: Pair<Int, Int>) {
        val spriteArea = listOf(lastCycle.second - 1, lastCycle.second, lastCycle.second + 1)
        pixels.add(if (crtPixelIndex.value() in spriteArea) "#" else ".")
    }

    class CrtPixelIndex {
        private var value: Int = 0

        fun value(): Int {
            val currentValue = value
            if (value == 39) value = 0 else value += 1
            return currentValue
        }
    }
}