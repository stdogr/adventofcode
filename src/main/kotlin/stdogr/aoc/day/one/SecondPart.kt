package stdogr.aoc.day.one

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
