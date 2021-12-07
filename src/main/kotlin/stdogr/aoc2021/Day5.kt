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
    val points = createPoints(lines)
        .filterNot { (start, end) -> start.first != end.first && start.second != end.second }

    return processPoints(points)
}

fun findDangerousVentsWithDiagonals(lines: List<String>): Int {
    val points = createPoints(lines)

    return processPoints(points)
}

private fun createPoints(lines: List<String>) = lines.filterNot { it.isBlank() }
    .map { line ->
        val pointData = line.split("->").map { it.trim() }
        val start = createPoint(pointData.first())
        val end = createPoint(pointData.last())
        start to end
    }

private fun processPoints(points: List<Pair<Pair<Int, Int>, Pair<Int, Int>>>): Int {
    val pointsAndHits = mutableMapOf<Pair<Int, Int>, Int>()

    points.forEach { (start, end) ->
        var currentX = start.first
        var currentY = start.second
        var diff = max(
            abs(start.first - end.first),
            abs(start.second - end.second)
        )
        while (diff >= 0) {
            val newPoint = currentX to currentY
            if (pointsAndHits.containsKey(newPoint)) {
                pointsAndHits[newPoint] = pointsAndHits[newPoint]!! + 1
            } else {
                pointsAndHits[newPoint] = 1
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

    return pointsAndHits.count { it.value > 1 }
}

private fun createPoint(data: String): Pair<Int, Int> {
    val split = data.split(",").map { it.trim() }
    return split.first().toInt() to split.last().toInt()
}
