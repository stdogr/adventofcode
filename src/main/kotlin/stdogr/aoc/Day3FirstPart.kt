package stdogr.aoc

import kotlin.math.pow

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
    for (index in 0 until 5) {
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
