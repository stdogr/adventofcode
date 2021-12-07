package stdogr.aoc2021

import stdogr.aoc2021.util.loadResourceLines
import kotlin.math.abs
import kotlin.math.max

fun main() {
    val values = loadResourceLines("2021/day5.txt")
    val resultPart1 = findDangerousVents(values)
    println("part 1: [$resultPart1]")

    val resultPart2 = findDangerousVentsWithDiagonals(values)
    println("part 2: [$resultPart2]")
}

fun findDangerousVents(lines: List<String>): Int {
    val points = mutableMapOf<Pair<Int, Int>, Int>()

    lines.filterNot { it.isBlank() }.forEach { line ->
        val pointData = line.split("->").map { it.trim() }
        val start = createPoint(pointData.first())
        val end = createPoint(pointData.last())
        if (start.first == end.first) {
            range(start.second, end.second).forEach { y ->
                val newPoint = start.first to y
                if (points.containsKey(newPoint)) {
                    points[newPoint] = points[newPoint]!! + 1
                } else {
                    points[newPoint] = 1
                }
            }
        } else if (start.second == end.second) {
            range(start.first, end.first).forEach { x ->
                val newPoint = x to end.second
                if (points.containsKey(newPoint)) {
                    points[newPoint] = points[newPoint]!! + 1
                } else {
                    points[newPoint] = 1
                }
            }
        }
    }

    return points.count { it.value > 1 }
}

fun findDangerousVentsWithDiagonals(lines: List<String>): Int {
    val points = mutableMapOf<Pair<Int, Int>, Int>()

    lines.filterNot { it.isBlank() }.forEach { line ->
        val pointData = line.split("->").map { it.trim() }
        val start = createPoint(pointData.first())
        val end = createPoint(pointData.last())

        var currentX = start.first
        var currentY = start.second
        var diff = max(
            abs(start.first - end.first),
            abs(start.second - end.second)
        )
        while (diff >= 0) {
            val newPoint = currentX to currentY
            if (points.containsKey(newPoint)) {
                points[newPoint] = points[newPoint]!! + 1
            } else {
                points[newPoint] = 1
            }

            currentX = if (currentX < end.first) currentX + 1
            else if (currentX > end.first) currentX - 1
            else currentX

            currentY = if (currentY < end.second) currentY + 1
            else if (currentY > end.second) currentY - 1
            else currentY

            diff -= 1
        }
    }

    return points.count { it.value > 1 }
}

private fun range(first: Int, second: Int): IntRange {
    return if (first > second) {
        second..first
    } else first..second
}

private fun createPoint(data: String): Pair<Int, Int> {
    val split = data.split(",").map { it.trim() }
    return split.first().toInt() to split.last().toInt()
}
