package stdogr.aoc.day.one

import stdogr.aoc.util.FileLoader

fun main() {
    val data = FileLoader().load("2021/day1.txt")
    val values = data.lines()
        .filter { it.isNotBlank() }
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
