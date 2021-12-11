package stdogr.aoc2021

import stdogr.aoc2021.util.loadResource

fun main() {
    val data = loadResource("2021/day8.txt")
    val resultPart1 = countOneFourSevenEight(data)
    println("part 1: [$resultPart1]")

    val resultPart2 = countOneFourSevenEight(data)
    println("part 2: [$resultPart2]")
}

fun countOneFourSevenEight(data: String): Int {
    return data.lines()
        .filterNot { it.isEmpty() }
        .map { line ->
            line.split("|").last()
        }
        .flatMap { it.split(" ") }
        .map { it.trim() }
        .filter { it.length in listOf(2, 4, 3, 7) }
        .size

}
