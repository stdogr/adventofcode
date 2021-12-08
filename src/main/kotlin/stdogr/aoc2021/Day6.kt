package stdogr.aoc2021

import stdogr.aoc2021.util.loadResource

fun main() {
    val data = loadResource("2021/day6.txt")
    val resultPart1 = lanternFish(data, 80)
    println("part 1: [$resultPart1]")

    val resultPart2 = lanternFish(data, 256)
    println("part 2: [$resultPart2]")
}

fun lanternFish(data: String, days: Int): Long {
    val initial = data.lines()
        .first()
        .split(",")
        .map { it.trim().toInt() }

    val fish = (0..8)
        .map { 0L }
        .toMutableList()

    initial.forEach { fish[it] = fish[it] + 1 }

    repeat(days) {
        val zero = fish[0]
        var current = zero
        (0..8)
            .reversed()
            .forEach { index ->
                val old = fish[index]
                fish[index] = current
                current = old
                if (index == 0) {
                    fish[6] = fish[6] + old
                }
            }
    }

    return fish.sum()
}
