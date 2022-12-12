package stdogr.aoc2022

import stdogr.aoc2021.util.loadResource

fun main() {
    val days = listOf(
        Day1(),
        Day2(),
        Day3(),
    )

    days.forEach {
        val example = loadResource("2022/${it.filePrefix}_example.txt")
        val input = loadResource("2022/${it.filePrefix}.txt")

        println("${it.filePrefix} - example 1: [${it.partOne(example)}]")
        println("${it.filePrefix} - part 1: [${it.partOne(input)}]")

        println("${it.filePrefix} - example 2: [${it.partTwo(example)}]")
        println("${it.filePrefix} - part 2: [${it.partTwo(input)}]")
    }
}