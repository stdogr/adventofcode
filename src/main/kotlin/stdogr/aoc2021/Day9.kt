package stdogr.aoc2021

import stdogr.aoc2021.util.loadResource

fun main() {
    val data = loadResource("2021/day9.txt")
    val resultPart1 = sumRiskLevels(data)
    println("part 1: [$resultPart1]")

    val resultPart2 = sumRiskLevels(data)
    println("part 2: [$resultPart2]")
}

fun sumRiskLevels(data: String): Int {
    val heightMap: List<List<Int>> = data.lines()
        .filterNot { it.isBlank() }
        .map { line -> line.toCharArray().map { value -> "$value".toInt() } }

    val lowPoints = mutableListOf<Int>()

    heightMap.forEachIndexed { y, row ->
        row.forEachIndexed { x, value ->
            if (isLowPoint(value, x, row.size, y, heightMap)) {
                lowPoints.add(value)
            }
        }
    }

    return lowPoints.sumOf { it + 1 }
}

private fun isLowPoint(value: Int, x: Int, rowSize: Int, y: Int, heightMap: List<List<Int>>): Boolean {
    return neighbors(x, rowSize - 1, y, heightMap.size - 1, heightMap)
        .find { it <= value } == null
}

private fun neighbors(x: Int, maxX: Int, y: Int, maxY: Int, heightMap: List<List<Int>>): List<Int> {
    val neighbors = mutableListOf<Int>()
    val left = x - 1
    val right = x + 1
    val up = y - 1
    val down = y + 1

    if (left >= 0) neighbors.add(heightMap[y][left])
    if (right <= maxX) neighbors.add(heightMap[y][right])
    if (up >= 0) neighbors.add(heightMap[up][x])
    if (down <= maxY) neighbors.add(heightMap[down][x])

    return neighbors
}
