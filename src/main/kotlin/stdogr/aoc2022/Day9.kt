package stdogr.aoc2022

import kotlin.math.abs

class Day9 : Task<Int>("9_rope_bridge") {

    override fun partOne(input: String): Int {
        return move(input, 2)
    }

    override fun partTwo(input: String): Int {
        return move(input, 10)
    }

    private fun move(input: String, ropeLength: Int): Int {
        val moveRegex = "([RLUD]) ([0-9]+)".toRegex()
        val rope = mutableListOf<Pair<Int, Int>>()
        repeat(ropeLength) {
            rope.add(Pair(10000000, 10000000)) // lazy fix to avoid negative coordinates
        }
        val visited = mutableSetOf<Pair<Int, Int>>()
        visited.add(rope.last())

        input.lines()
            .forEach { line ->
                val find = moveRegex.find(line)!!
                val direction = find.groups[1]!!.value
                val steps = find.groups[2]!!.value.toInt()

                repeat(steps) {
                    rope.forEachIndexed { index, _ ->
                        if (index == 0) {
                            when (direction) {
                                "U" -> rope[index] = Pair(rope[index].first, rope[index].second + 1)
                                "D" -> rope[index] = Pair(rope[index].first, rope[index].second - 1)
                                "L" -> rope[index] = Pair(rope[index].first - 1, rope[index].second)
                                "R" -> rope[index] = Pair(rope[index].first + 1, rope[index].second)
                            }
                        } else {
                            move(rope, index)
                        }
                    }
                    visited.add(rope.last())

                }
            }
        return visited.size
    }

    private fun move(rope: MutableList<Pair<Int, Int>>, index: Int): String {
        val previousSegment = rope[index - 1]
        val currentSegment = rope[index]

        if (abs(abs(previousSegment.first) - abs(currentSegment.first)) > 1) {
            if (previousSegment.first > currentSegment.first) {
                if (previousSegment.second > currentSegment.second) {
                    rope[index] = Pair(currentSegment.first + 1, currentSegment.second + 1)
                    return "RU"
                } else if (previousSegment.second < currentSegment.second) {
                    rope[index] = Pair(currentSegment.first + 1, currentSegment.second - 1)
                    return "RD"
                } else {
                    rope[index] = Pair(currentSegment.first + 1, currentSegment.second)
                    return "R"
                }
            } else {
                if (previousSegment.second > currentSegment.second) {
                    rope[index] = Pair(currentSegment.first - 1, currentSegment.second + 1)
                    return "LU"
                } else if (previousSegment.second < currentSegment.second) {
                    rope[index] = Pair(currentSegment.first - 1, currentSegment.second - 1)
                    return "LD"
                } else {
                    rope[index] = Pair(currentSegment.first - 1, currentSegment.second)
                    return "L"
                }
            }
        } else if (abs(abs(previousSegment.second) - abs(currentSegment.second)) > 1) {
            if (previousSegment.second > currentSegment.second) {
                if (previousSegment.first > currentSegment.first) {
                    rope[index] = Pair(currentSegment.first + 1, currentSegment.second + 1)
                    return "RU"
                } else if (previousSegment.first < currentSegment.first) {
                    rope[index] = Pair(currentSegment.first - 1, currentSegment.second + 1)
                    return "LU"
                } else {
                    rope[index] = Pair(currentSegment.first, currentSegment.second + 1)
                    return "U"
                }
            } else {
                if (previousSegment.first > currentSegment.first) {
                    rope[index] = Pair(currentSegment.first + 1, currentSegment.second - 1)
                    return "RD"
                } else if (previousSegment.first < currentSegment.first) {
                    rope[index] = Pair(currentSegment.first - 1, currentSegment.second - 1)
                    return "LD"
                } else {
                    rope[index] = Pair(currentSegment.first, currentSegment.second - 1)
                    return "D"
                }
            }
        }
        return "not moved"
    }
}