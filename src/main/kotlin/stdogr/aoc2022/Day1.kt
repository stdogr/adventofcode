package stdogr.aoc2022


class Day1 : Day<Int>("day1") {

    override fun partOne(input: String): Int {
        val numbers = sum(input)

        return numbers.maxOf { it }
    }

    override fun partTwo(input: String): Int {
        val numbers = sum(input)
        val sorted = numbers.sortedDescending()

        return sorted.take(3).sum()
    }

    private fun sum(input: String): MutableList<Int> {
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
        return numbers
    }
}
