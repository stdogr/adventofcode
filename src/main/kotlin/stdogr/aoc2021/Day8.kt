package stdogr.aoc2021

import stdogr.util.loadResource

fun main() {
    val data = loadResource("2021/day8.txt")
    val resultPart1 = countOneFourSevenEight(data)
    println("part 1: [$resultPart1]")

    val resultPart2 = sumAllOutputs(data)
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

fun sumAllOutputs(data: String): Int {
    return data.lines()
        .filterNot { it.isEmpty() }
        .map { line -> solveLine(line) }
        .sumOf { it }
}

fun solveLine(line: String): Int {
    val split = line.split("|")
    val patterns = split
        .flatMap { it.split(" ") }
        .filterNot { it.isBlank() }
        .map { it.toSortedSet().joinToString("") }
        .toSet()

    val keys = findKeys(patterns)

    return split
        .last()
        .split(" ")
        .filterNot { it.isBlank() }
        .map { it.toSortedSet().joinToString("") }
        .map { keys[it] }
        .joinToString("")
        .toInt()
}

private fun findKeys(patterns: Set<String>): Map<String, Int> {
    val keys = mutableMapOf<Int, String>()
    keys[1] = patterns.first { it.length == 2 }
    keys[4] = patterns.first { it.length == 4 }
    keys[7] = patterns.first { it.length == 3 }
    keys[8] = patterns.first { it.length == 7 }

    keys[9] = patterns.filter { it.length == 6 }
        .first { it.toList().containsAll(keys[4]!!.toList()) }

    keys[0] = patterns.filter { it.length == 6 }
        .filterNot { it == keys[9] }
        .first { it.toList().containsAll(keys[1]!!.toList()) }

    keys[6] = patterns.filter { it.length == 6 }
        .filterNot { it == keys[9] }
        .filterNot { it == keys[0] }
        .first()

    keys[3] = patterns.filter { it.length == 5 }
        .first { it.toList().containsAll(keys[1]!!.toList()) }

    keys[5] = patterns.filter { it.length == 5 }
        .filterNot { it == keys[3] }
        .first {
            val leftOverLetters = it.toMutableList()
            leftOverLetters.removeAll(keys[4]!!.toList())
            leftOverLetters.size == 2
        }

    keys[2] = patterns.filter { it.length == 5 }
        .filterNot { it == keys[3] }
        .filterNot { it == keys[5] }
        .first()

    return keys.map { it.value to it.key }
        .toMap()
}
