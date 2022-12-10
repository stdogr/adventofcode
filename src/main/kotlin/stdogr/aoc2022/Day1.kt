package stdogr.aoc2022


class Day1 : Day<Int>("day1") {

    override fun partOne(input: String): Int {
        val lines = input
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

    override fun partTwo(input: String): Int {
        val lines = input
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
