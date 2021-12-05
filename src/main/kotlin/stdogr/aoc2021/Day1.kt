package stdogr.aoc2021

import stdogr.aoc2021.util.FileLoader

fun main() {
    val values = FileLoader().loadLines("2021/day1.txt")
        .map { it.toInt() }
    val countPart1 = countNumberOfTimesValuesIncrease(values)
    println("part 1: [$countPart1]")

    val countPart2 = countNumberOfTimesValuesIncreaseWindowed(values)
    println("part 2: [$countPart2]")
}

fun countNumberOfTimesValuesIncrease(values: List<Int>): Int {
    if (values.isEmpty()) return 0
    var count = 0
    for (index in 1 until values.size) {
        val previous = values[index - 1]
        val current = values[index]
        if (previous < current) count += 1
    }
    return count
}

fun countNumberOfTimesValuesIncreaseWindowed(values: List<Int>): Int {
    if (values.size < 4) return 0
    val windows = mutableListOf<Int>()
    for (index in 2 until values.size) {
        val first = values[index - 2]
        val second = values[index - 1]
        val current = values[index]
        windows.add(first + second + current)
    }
    return countNumberOfTimesValuesIncrease(windows)
}
