package stdogr.aoc2021

import stdogr.util.loadResource

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
    return (0..max).map { newPosition ->
        val (below, sameOrAbove) = crabs.partition { it < newPosition }
        below.sumOf { costFunction(newPosition - it) } + sameOrAbove.sumOf { costFunction(it - newPosition) }
    }.minOf { it }
}
