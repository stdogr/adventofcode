package stdogr.aoc2021

import stdogr.util.loadResource

fun main() {
    val data = loadResource("2021/day10.txt")
    val resultPart1 = scoreCorrupted(data)
    println("part 1: [$resultPart1]")

    val resultPart2 = scoreIncomplete(data)
    println("part 2: [$resultPart2]")
}

fun scoreCorrupted(data: String): Long {
    return data.lines()
        .filterNot { it.isBlank() }
        .mapNotNull { soreLineCorrupted(it) }
        .sumOf { it.scoreCorrupted() }
}

fun scoreIncomplete(data: String): Long {
    val scores = data.lines()
        .filterNot { it.isBlank() }
        .mapNotNull { scoreLineIncomplete(it) }
        .sorted()

    return scores[(scores.size - 1) / 2]
}

private fun soreLineCorrupted(line: String): Bracket? {
    val chars = line.toCharArray()
        .map { Bracket(it) }
        .toMutableList()
    val processedSequence = mutableListOf(chars.first())
    val charsToProcess = chars.subList(1, chars.size - 1)
    charsToProcess
        .forEach {
            if (it.isOpen()) {
                processedSequence.add(it)
            } else if (processedSequence.last().isOpposite(it)) {
                processedSequence.removeLast()

            } else return it
        }
    return null
}

private fun scoreLineIncomplete(line: String): Long? {
    val chars = line.toCharArray()
        .map { Bracket(it) }
        .toMutableList()
    val processedSequence = mutableListOf(chars.first())
    val charsToProcess = chars.subList(1, chars.size)
    charsToProcess.forEach {
        if (it.isOpen()) {
            processedSequence.add(it)
        } else if (processedSequence.last().isOpposite(it)) {
            processedSequence.removeLast()

        } else return null
    }

    var score = 0L
    val completion = processedSequence.reversed().map { it.reverse() }
    completion
        .forEach {
            score = (score * 5) + it.scoreIncomplete()
        }

    return score
}

private data class Bracket(val value: Char) {
    fun isOpposite(other: Bracket): Boolean {
        return (other.value == ')' && value == '(') ||
                (other.value == '}' && value == '{') ||
                (other.value == '>' && value == '<') ||
                (other.value == ']' && value == '[')
    }

    fun isOpen(): Boolean {
        return value == '(' ||
                value == '{' ||
                value == '[' ||
                value == '<'
    }

    fun scoreCorrupted(): Long {
        if (value == ')') return 3
        if (value == ']') return 57
        if (value == '}') return 1197
        if (value == '>') return 25137
        throw IllegalStateException("Cannot score bracket: [$this]!")
    }

    fun reverse(): Bracket {
        if (value == '(') return Bracket(')')
        if (value == '{') return Bracket('}')
        if (value == '<') return Bracket('>')
        if (value == '[') return Bracket(']')
        throw IllegalStateException("Cannot reverse bracket: [$this]!")
    }

    fun scoreIncomplete(): Long {
        if (value == ')') return 1
        if (value == ']') return 2
        if (value == '}') return 3
        if (value == '>') return 4
        throw IllegalStateException("Cannot score bracket: [$this]!")
    }
}
