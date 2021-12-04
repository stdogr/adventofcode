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
        val count = countNumberOfTimeValuesIncrease(values)
        println(count)
    }
}

fun countNumberOfTimeValuesIncrease(values: List<Int>): Int {
    if (values.isEmpty()) return 0
    var count = 0
    for (index in 1 until values.size) {
        val previous = values[index - 1]
        val current = values[index]
        if (previous < current) count += 1
    }
    return count
}
