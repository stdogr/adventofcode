package stdogr.aoc2022

import stdogr.aoc2021.util.loadResource

fun main() {
    val days = listOf(
        Day1(),
        Day2(),
    )

    days.forEach {
        val example = loadResource("2022/${it.day}example.txt")
        val input = loadResource("2022/${it.day}.txt")

        println("${it.day} - example 1: [${it.partOne(example)}]")
        println("${it.day} - part 1: [${it.partOne(input)}]")

        println("${it.day} - example 2: [${it.partTwo(example)}]")
        println("${it.day} - part 2: [${it.partTwo(input)}]")
    }
}