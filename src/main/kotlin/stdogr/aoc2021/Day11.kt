package stdogr.aoc2021

import stdogr.util.loadResource

fun main() {
    val data = loadResource("2021/day11.txt")
    val resultPart1 = countFlashes(data, 100)
    println("part 1: [$resultPart1]")

    val resultPart2 = simultaneousFlash(data)
    println("part 2: [$resultPart2]")
}

fun countFlashes(data: String, steps: Int): Long {
    val matrix = data.lines()
        .filterNot { it.isBlank() }
        .map { line -> line.toCharArray().map { Octopus("$it".toInt()) } }

    var flashes = 0L

    (1..steps).forEach { step ->
        matrix.forEach { row ->
            row.forEach { octopus -> octopus.bump() }
        }

        var done = false
        while (!done) {
            var flashed = false
            matrix.forEachIndexed { y, row ->
                row.forEachIndexed { x, octopus ->
                    if (octopus.flash()) {
                        bumpNeighbors(x, y, row.size - 1, matrix.size - 1, matrix)
                        flashes += 1
                        flashed = true
                    }
                }
            }
            done = !flashed
        }

        matrix.forEach { row ->
            row.forEach { octopus -> octopus.reset() }
        }
    }

    return flashes
}

fun simultaneousFlash(data: String): Long {
    val matrix = data.lines()
        .filterNot { it.isBlank() }
        .map { line -> line.toCharArray().map { Octopus("$it".toInt()) } }

    (1..100000L).forEach { step ->
        matrix.forEach { row ->
            row.forEach { octopus -> octopus.bump() }
        }

        var flashes = 0

        var done = false
        while (!done) {
            var flashed = false
            matrix.forEachIndexed { y, row ->
                row.forEachIndexed { x, octopus ->
                    if (octopus.flash()) {
                        bumpNeighbors(x, y, row.size - 1, matrix.size - 1, matrix)
                        flashes += 1
                        flashed = true
                    }
                }
            }
            done = !flashed
        }

        if (flashes == matrix.size * matrix.first().size) {
            return step
        }

        matrix.forEach { row ->
            row.forEach { octopus -> octopus.reset() }
        }
    }

    throw IllegalArgumentException("No simultaneous flashes!")
}

private fun bumpNeighbors(x: Int, y: Int, maxX: Int, maxY: Int, matrix: List<List<Octopus>>) {
    val left = x - 1
    val right = x + 1
    val up = y - 1
    val down = y + 1

    if (up >= 0 && left >= 0) matrix[up][left].bump()
    if (up >= 0) matrix[up][x].bump()
    if (up >= 0 && right <= maxX) matrix[up][right].bump()

    if (left >= 0) matrix[y][left].bump()
    if (right <= maxX) matrix[y][right].bump()

    if (down <= maxY && left >= 0) matrix[down][left].bump()
    if (down <= maxY) matrix[down][x].bump()
    if (down <= maxY && right <= maxX) matrix[down][right].bump()
}

private data class Octopus(var energy: Int) {

    var flashed = false

    fun bump() {
        energy += 1
    }

    fun flash(): Boolean {
        return if (energy > 9 && !flashed) {
            flashed = true
            true
        } else false
    }

    fun reset() {
        if (energy > 9) energy = 0
        flashed = false
    }

}
