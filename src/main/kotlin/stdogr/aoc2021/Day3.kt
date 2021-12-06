package stdogr.aoc2021

import stdogr.aoc2021.util.loadResourceLines
import kotlin.math.pow

fun main() {
    val bits = loadResourceLines("2021/day3.txt")
    val resultPart1 = findProductOfGammaAndEpsilon(bits)
    println("part 1: [$resultPart1]")

    val resultPart2 = findLifeSupportRating(bits)
    println("part 2: [$resultPart2]")
}

fun findProductOfGammaAndEpsilon(bits: List<String>): Int {
    val gammaAndEpsilonDiagnostic = GammaAndEpsilonDiagnostic(bits)
    val gamma = convertToInt(gammaAndEpsilonDiagnostic.findGamma())
    val epsilon = convertToInt(gammaAndEpsilonDiagnostic.findEpsilon())
    return gamma * epsilon
}

fun findLifeSupportRating(bits: List<String>): Int {
    val generatorRating = findOxygenGeneratorRating(bits)
    val scrubberRating = findOxygenScrubberRating(bits)
    return generatorRating * scrubberRating
}

fun findOxygenGeneratorRating(bits: List<String>): Int {
    val filtered = filter(bits, '1', '0')
    val result = filtered.first()
    return convertToInt(result)
}

fun findOxygenScrubberRating(bits: List<String>): Int {
    val filtered = filter(bits, '0', '1')
    val result = filtered.first()
    return convertToInt(result)
}

private fun filter(bits: List<String>, mostCommonChar: Char, leastCommonChar: Char): List<String> {
    var filtered = bits
    for (run in 0 until bits.first().length) {
        var counter = 0
        filtered.forEach { bitString ->
            when (bitString[run]) {
                '0' -> counter -= 1
                '1' -> counter += 1
                else -> throw IllegalArgumentException("Malformed bit in: [$bitString]!")
            }
        }
        val filterBit = if (counter >= 0) mostCommonChar else leastCommonChar
        filtered = filtered.filter { it[run] == filterBit }
        if (filtered.size == 1) return filtered
    }
    require(filtered.size == 1)
    return filtered
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

data class GammaAndEpsilonDiagnostic(
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

    fun findGamma(): String {
        return counters.map {
            if (it > 0) 1 else 0
        }.joinToString(separator = "")
    }

    fun findEpsilon(): String {
        return counters.map {
            if (it < 0) 1 else 0
        }.joinToString(separator = "")
    }
}
