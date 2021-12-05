package stdogr.aoc2021

import stdogr.aoc2021.util.FileLoader

fun main() {
    val values = FileLoader().loadLines("2021/day1.txt")
        .map { it.toInt() }
    val count = countNumberOfTimesValuesIncrease(values)
    println(count)
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
