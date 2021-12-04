package stdogr.aoc

fun main() {
    Loader().countNumberOfTimeValuesIncrease()
}

class Loader {

    fun countNumberOfTimeValuesIncrease() {
        val data = this::javaClass.get().classLoader
            .getResource("day1.txt")!!.readText()
        val values = data.lines()
            .filter { it.isNotBlank() }
            .map { it.toInt() }
        val count = countNumberOfTimesValuesIncrease(values)
        println(count)
    }
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
