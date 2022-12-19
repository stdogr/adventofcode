package stdogr.aoc2021

import stdogr.util.loadResource

fun main() {
    val data = loadResource("2021/day9.txt")
    val resultPart1 = sumRiskLevels(data)
    println("part 1: [$resultPart1]")

    val resultPart2 = multiplyThreeLargestBasins(data)
    println("part 2: [$resultPart2]")
}

fun sumRiskLevels(data: String): Int {
    val heightMap: List<List<Int>> = data.lines()
        .filterNot { it.isBlank() }
        .map { line -> line.toCharArray().map { value -> "$value".toInt() } }

    val lowPoints = mutableListOf<Int>()

    heightMap.forEachIndexed { y, row ->
        row.forEachIndexed { x, value ->
            if (isLowPoint(Point(x, y, value), row.size, heightMap)) {
                lowPoints.add(value)
            }
        }
    }

    return lowPoints.sumOf { it + 1 }
}

fun multiplyThreeLargestBasins(data: String): Int {
    val heightMap: List<List<Int>> = data.lines()
        .filterNot { it.isBlank() }
        .map { line -> line.toCharArray().map { value -> "$value".toInt() } }

    val basins = mutableListOf<List<Point>>()

    heightMap.forEachIndexed { y, row ->
        row.forEachIndexed { x, value ->
            val currentPoint = Point(x, y, value)
            if (isLowPoint(Point(x, y, value), row.size, heightMap)) {
                val basin = fillBasin(currentPoint, row.size - 1, heightMap)
                basins.add(basin)
            }
        }
    }

    val basinSizes = basins.map { it.size }.sorted()

    return basinSizes[basinSizes.size - 1] * basinSizes[basinSizes.size - 2] * basinSizes[basinSizes.size - 3]
}

private fun isLowPoint(point: Point, rowSize: Int, heightMap: List<List<Int>>): Boolean {
    return neighbors(point, rowSize - 1, heightMap.size - 1, heightMap)
        .find { it.value <= point.value } == null
}

private fun fillBasin(
    lowPoint: Point,
    maxX: Int,
    heightMap: List<List<Int>>
): List<Point> {
    val basin = mutableListOf<Point>()
    basin.add(lowPoint)

    var index = 0
    while (index < basin.size) {
        val current = basin[index]
        neighbors(current, maxX, heightMap.size - 1, heightMap)
            .filter {
                val neighborValue = heightMap[it.y][it.x]
                neighborValue != 9 && neighborValue > heightMap[current.y][current.x]
            }.forEach {
                if (!basin.contains(it)) basin.add(it)
            }
        index++
    }
    return basin
}

private fun neighbors(point: Point, maxX: Int, maxY: Int, heightMap: List<List<Int>>): List<Point> {
    val neighbors = mutableListOf<Point>()
    val left = point.x - 1
    val right = point.x + 1
    val up = point.y - 1
    val down = point.y + 1

    if (left >= 0) neighbors.add(Point(left, point.y, heightMap[point.y][left]))
    if (right <= maxX) neighbors.add(Point(right, point.y, heightMap[point.y][right]))
    if (up >= 0) neighbors.add(Point(point.x, up, heightMap[up][point.x]))
    if (down <= maxY) neighbors.add(Point(point.x, down, heightMap[down][point.x]))

    return neighbors
}

data class Point(
    val x: Int,
    val y: Int,
    val value: Int
)
