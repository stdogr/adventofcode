package stdogr.aoc2021

import stdogr.aoc2021.util.loadResource

fun main() {
    val data = loadResource("2021/day4.txt")
    val result1 = Bingo(data).scoreFirstWinner()
    println("part 1: [$result1]")

    val result2 = Bingo(data).scoreLastWinner()
    println("part 2: [$result2]")
}

class Bingo(data: String) {

    private val draws: List<Int>
    private val boards: List<Board>

    init {
        val split = data.split("\\n\\n".toRegex())
            .filter { it.isNotBlank() }
        val drawData = split.first()
        val boardData = split.takeLast(split.size - 1)

        draws = drawData.split(",").map { it.toInt() }
        boards = boardData.map { Board(it) }
    }

    fun scoreFirstWinner(): Int {
        draws.forEach { draw ->
            boards.forEach { board ->
                if (board.call(draw)) {
                    return board.score(draw)
                }
            }
        }
        throw IllegalStateException("no winner")
    }

    fun scoreLastWinner(): Int {
        draws.forEach { draw ->
            boards.forEach { board ->
                board.call(draw)
            }
        }
        draws.reversed()
            .forEach { draw ->
                boards.forEach { board ->
                    if (board.revertCall(draw)) {
                        return board.score(draw)
                    }
                }
            }
        throw IllegalStateException("no winner")
    }
}

class Board(boardData: String) {

    private val data = parse(boardData)

    private fun parse(boardData: String): List<List<Cell>> {
        return boardData.lines()
            .filter { it.isNotBlank() }
            .map { line ->
                line.split(" ")
                    .filter { it.isNotBlank() }
                    .map { numbers -> Cell(numbers.toInt()) }
            }
    }

    fun call(draw: Int): Boolean {
        data.forEachIndexed { rowIndex, rows ->
            rows.forEachIndexed { cellIndex, cell ->
                if (cell.call(draw)) {
                    if (checkBingo(rowIndex, cellIndex)) return true
                }
            }
        }
        return false
    }

    private fun checkBingo(rowIndex: Int, cellIndex: Int): Boolean {
        return if (checkHorizontal(rowIndex)) {
            true
        } else {
            checkVertical(cellIndex)
        }
    }

    private fun checkVertical(cellIndex: Int): Boolean {
        return data[0][cellIndex].called &&
                data[1][cellIndex].called &&
                data[2][cellIndex].called &&
                data[3][cellIndex].called &&
                data[4][cellIndex].called
    }

    private fun checkHorizontal(rowIndex: Int): Boolean {
        return data[rowIndex][0].called &&
                data[rowIndex][1].called &&
                data[rowIndex][2].called &&
                data[rowIndex][3].called &&
                data[rowIndex][4].called
    }

    fun score(draw: Int): Int {
        return data.flatten()
            .filterNot { it.called }
            .sumOf { it.number } * draw
    }

    fun revertCall(draw: Int): Boolean {
        data.forEach { rows ->
            rows.forEach { cell ->
                if (cell.revertCall(draw)) {
                    if (checkDebingo()) {
                        cell.call(draw)
                        return true
                    }
                }
            }
        }
        return false
    }

    private fun checkDebingo(): Boolean {
        var bingo = false
        for (rowIndex in 0..4) {
            if (checkHorizontal(rowIndex)) bingo = true
        }
        for (cellIndex in 0..4) {
            if (checkVertical(cellIndex)) bingo = true
        }
        return !bingo
    }
}

class Cell(
    val number: Int,
    called: Boolean = false
) {

    var called = called
        private set

    fun call(draw: Int): Boolean {
        if (draw == number) {
            called = true
            return true
        }
        return false
    }

    fun revertCall(draw: Int): Boolean {
        if (draw == number) {
            called = false
            return true
        }
        return false
    }
}
