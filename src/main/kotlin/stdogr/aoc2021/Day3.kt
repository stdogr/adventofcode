package stdogr.aoc2021

import stdogr.aoc2021.util.FileLoader
import kotlin.math.pow

fun main() {
    val bits = FileLoader().loadLines("2021/day3.txt")
    val resultPart1 = findProductOfGammaAndEpsilon(bits)
    println("part 1: [$resultPart1]")
}

fun findProductOfGammaAndEpsilon(bits: List<String>): Int {
    val diagnosticReport = DiagnosticReport(bits)
    val gamma = convertToInt(diagnosticReport.mostCommonBits())
    val epsilon = convertToInt(diagnosticReport.leastCommonBits())
    return gamma * epsilon
}

fun convertToInt(bits: String): Int {
    var int = 0
    bits.reversed()
        .forEachIndexed { index, bit ->
            if (bit == '1') {
                int += 2.0.pow(index).toInt()
            }
        }
    return int
}

data class DiagnosticReport(
    val bits: List<String>
) {

    private val counters = initCounters()

    private fun initCounters(): List<Int> {
        require(bits.isNotEmpty())
        val counters = (0 until bits.first().length)
            .map { 0 }
            .toMutableList()

        bits.forEach { bitString ->
            bitString.forEachIndexed { index, bit ->
                when (bit) {
                    '0' -> counters[index] = counters[index] - 1
                    '1' -> counters[index] = counters[index] + 1
                    else -> throw IllegalArgumentException("Malformed bit in: [$bitString]!")
                }
            }
        }
        return counters
    }

    fun mostCommonBits(): String {
        return counters.map {
            if (it > 0) 1 else 0
        }.joinToString(separator = "")
    }

    fun leastCommonBits(): String {
        return counters.map {
            if (it < 0) 1 else 0
        }.joinToString(separator = "")
    }
}
