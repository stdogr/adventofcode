package stdogr.aoc2022

import stdogr.aoc2021.util.loadResource

fun main() {
    val input = loadResource("2022/day1.txt")

    val result1 = Day1().partOne(input)
    println("part 1: [$result1]")

    val result2 = Day1().partTwo(input)
    println("part 2: [$result2]")
}

class Day1 {

    fun partOne(values: String): Int {
        val lines = values
            .lines()

        val numbers = mutableListOf<Int>()

        var current = 0
        lines.forEach {
            if (it.isNotBlank()) {
                current += it.toInt()
            } else {
                numbers.add(current)
                current = 0
            }
        }
        return numbers.maxOf { it }
    }

    fun partTwo(values: String): Int {
        val lines = values
            .lines()

        val numbers = mutableListOf<Int>()

        var current = 0
        lines.forEach {
            if (it.isNotBlank()) {
                current += it.toInt()
            } else {
                numbers.add(current)
                current = 0
            }
        }

        val sorted = numbers.sortedDescending()

        return sorted[0] + sorted[1] + sorted[2]
    }
}
