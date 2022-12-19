package stdogr.aoc2021

import stdogr.util.loadResource

fun main() {
    val data = loadResource("2021/day13.txt")
    val resultPart1 = countVisibleDotsAfterFirstFold(data)
    println("part 1: [$resultPart1]")

    val resultPart2 = foldAll(data)
    //..##.####..##..#..#..##..###..###..###.
    //...#....#.#..#.#..#.#..#.#..#.#..#.#..#
    //...#...#..#....#..#.#..#.#..#.#..#.###.
    //...#..#...#.##.#..#.####.###..###..#..#
    //#..#.#....#..#.#..#.#..#.#....#.#..#..#
    //.##..####..###..##..#..#.#....#..#.###.
    println("part 2: [$resultPart2]")
}

fun countVisibleDotsAfterFirstFold(data: String): Int {
    var (points, foldInstructions) = parseInput(data)

    points = points.mapNotNull { it.fold(foldInstructions.first()) }.toSet()

    return points.size
}

fun foldAll(data: String): Int {
    var (points, foldInstructions) = parseInput(data)

    foldInstructions.forEach { fold ->
        points = points.mapNotNull { it.fold(fold) }.toSet()
    }

    val maxX = points.maxOf { it.x }
    val maxY = points.maxOf { it.y }

    val matrix = (0..maxY).map { yIndex ->
        (0..maxX).map { xIndex ->
            if (points.any { it.x == xIndex && it.y == yIndex }) {
                "#"
            } else "."
        }
    }

    matrix.forEach { row ->
        row.forEach { print(it) }
        println()
    }

    return points.size
}

private fun parseInput(data: String): Pair<Set<FoldablePoint>, List<Pair<String, Long>>> {
    val (folds, coords) = data.lines()
        .filterNot { it.isBlank() }
        .partition { it.startsWith("fold") }

    val points = coords.map {
        val split = it.split(",")
        FoldablePoint(split.first().toLong(), split.last().toLong())
    }.toSet()

    val foldInstructions = folds.map {
        val fold = it.split(" ").last()
        val axisToIndex = fold.split("=")
        axisToIndex.first() to axisToIndex.last().toLong()
    }
    return points to foldInstructions
}

data class FoldablePoint(
    val x: Long,
    val y: Long,
) {

    fun fold(axisToIndex: Pair<String, Long>): FoldablePoint? {
        val axis = axisToIndex.first
        val index = axisToIndex.second

        val (newX, newY) = if (axis == "y" && y > index) {
            x to index - (y - index)
        } else if (axis == "x" && x > index) {
            index - (x - index) to y
        } else x to y

        return if (newX < 0 || newY < 0) null else FoldablePoint(newX, newY)
    }
}
