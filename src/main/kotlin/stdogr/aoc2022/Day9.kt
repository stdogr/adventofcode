package stdogr.aoc2022

import kotlin.math.abs

class Day9 : Task<Int>("9_rope_bridge") {

    override fun partOne(input: String): Int {
        val moveRegex = "([RLUD]) ([0-9]+)".toRegex()

        val visited = mutableSetOf<Pair<Int, Int>>()
        // laziest fix for movement with negative coordinates
        var head = Pair(10000000, 10000000)
        var tail = Pair(10000000, 10000000)
        visited.add(tail)

        input.lines()
            .forEach { line ->
                val find = moveRegex.find(line)!!
                val direction = find.groups[1]!!.value
                val steps = find.groups[2]!!.value.toInt()

                repeat(steps) {
                    when (direction) {
                        "U" -> {
                            head = Pair(head.first, head.second + 1)
                            if (abs(abs(head.second) - abs(tail.second)) > 1) {
                                tail = Pair(head.first, head.second - 1)
                                visited.add(tail)
                            }
                        }

                        "D" -> {
                            head = Pair(head.first, head.second - 1)
                            if (abs(abs(head.second) - abs(tail.second)) > 1) {
                                tail = Pair(head.first, head.second + 1)
                                visited.add(tail)
                            }
                        }

                        "L" -> {
                            head = Pair(head.first - 1, head.second)
                            if (abs(abs(head.first) - abs(tail.first)) > 1) {
                                tail = Pair(head.first + 1, head.second)
                                visited.add(tail)
                            }
                        }

                        "R" -> {
                            head = Pair(head.first + 1, head.second)
                            if (abs(abs(head.first) - abs(tail.first)) > 1) {
                                tail = Pair(head.first - 1, head.second)
                                visited.add(tail)
                            }
                        }
                    }
                }
            }

        return visited.size
    }

    override fun partTwo(input: String): Int {
        TODO()
    }
}