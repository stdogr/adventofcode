package stdogr.aoc2021

import stdogr.aoc2021.util.FileLoader
import kotlin.math.pow

fun main() {
    val bits = FileLoader().loadLines("2021/day3.txt")
    val resultPart1 = findProductOfGammaAndEpsilon(bits)
    println("part 1: [$resultPart1]")
}

fun findProductOfGammaAndEpsilon(bits: List<String>): Int {
    val gamma = findGamma(bits)
    val epsilon = findEpsilon(bits)
    return gamma * epsilon
}

fun findEpsilon(bits: List<String>): Int {
    return produceSequenceByMostCommonBit(bits) { zeroes, ones ->
        if (zeroes < ones) 0 else 1
    }
}

fun findGamma(bits: List<String>): Int {
    return produceSequenceByMostCommonBit(bits) { zeroes, ones ->
        if (zeroes > ones) 0 else 1
    }
}

private fun produceSequenceByMostCommonBit(bits: List<String>, pickZeroOrOne: (Int, Int) -> Int): Int {
    var result = ""
    for (index in bits.first().indices) {
        var zeroes = 0
        var ones = 0
        bits.forEach {
            val bit = it[index]
            if (bit == '0') {
                zeroes += 1
            } else if (bit == '1') {
                ones += 1
            }
        }
        result += pickZeroOrOne(zeroes, ones)
    }
    return convertToInt(result)
}

private fun convertToInt(bits: String): Int {
    var int = 0
    bits.reversed()
        .forEachIndexed { index, bit ->
            if (bit == '1') {
                int += 2.0.pow(index).toInt()
            }
        }
    return int
}
