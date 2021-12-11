package stdogr.aoc2021

import stdogr.aoc2021.util.loadResource

fun main() {
    val data = loadResource("2021/day7.txt")
    val resultPart1 = fuelCrabs(data) { it }
    println("part 1: [$resultPart1]")

    val resultPart2 = fuelCrabs(data) { position -> (1..position).sumOf { it } }
    println("part 2: [$resultPart2]")
}

fun fuelCrabs(data: String, costFunction: (Int) -> Int): Int {
    val crabs = data.lines()
        .first()
        .split(",")
        .map { it.toInt() }
        .sorted()

    val max = crabs.maxOf { it }
    val costs = (0..max).map { 0 }
        .toMutableList()

    costs.forEachIndexed { index, _ ->
        val (below, sameOrAbove) = crabs.partition { it < index }
        val cost = below.sumOf { costFunction(index - it) } + sameOrAbove.sumOf { costFunction(it - index) }
        costs[index] = cost
    }

    return costs.minOf { it }
}
