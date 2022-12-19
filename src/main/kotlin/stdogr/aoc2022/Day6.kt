package stdogr.aoc2022

import kotlin.streams.toList

class Day6 : Task<Int>("6_tuning_trouble") {

    override fun partOne(input: String): Int {
        return findMarker(input, 4)
    }

    override fun partTwo(input: String): Int {
        return findMarker(input, 14)
    }

    private fun findMarker(input: String, markerSize: Int): Int {
        var marker = markerSize
        input.chars()
            .toList()
            .windowed(markerSize, 1)
            .forEach {
                if (it.toSet().size == markerSize) {
                    return marker
                } else marker += 1
            }
        throw IllegalStateException("No marker found!")
    }
}