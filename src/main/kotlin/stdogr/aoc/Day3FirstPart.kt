package stdogr.aoc

import kotlin.math.pow

fun findEpsilon(bits: List<String>): Int {
    var result = ""
    for (index in 0 until 5) {
        val (zeroes, ones) = countBits(bits, index)
        if (zeroes < ones) {
            result += 0
        } else {
            result += 1
        }
    }
    return convertToInt(result)
}

fun findGamma(bits: List<String>): Int {
    var result = ""
    for (index in 0 until 5) {
        val (zeroes, ones) = countBits(bits, index)
        if (zeroes > ones) {
            result += 0
        } else {
            result += 1
        }
    }
    return convertToInt(result)
}

fun countBits(bits: List<String>, index: Int): Pair<Int, Int> {
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
    return zeroes to ones
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
