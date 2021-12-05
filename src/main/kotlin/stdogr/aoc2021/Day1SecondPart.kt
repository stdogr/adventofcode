package stdogr.aoc2021

import stdogr.aoc2021.util.FileLoader

fun main() {
    val values = FileLoader().loadLines("2021/day1.txt")
        .map { it.toInt() }
    val count = countNumberOfTimesValuesIncreaseWindowed(values)
    println(count)
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
