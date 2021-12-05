package stdogr.aoc2021

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

    fun play(): Int {
        draws.forEach { draw ->
            boards.forEach { board ->
                if (board.call(draw)) {
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

    private fun checkDiagonal(): Boolean {
        return if (
            data[0][0].called &&
            data[1][1].called &&
            data[2][2].called &&
            data[3][3].called &&
            data[4][4].called
        ) {
            true
        } else {
            data[0][4].called &&
                    data[1][3].called &&
                    data[2][2].called &&
                    data[3][1].called &&
                    data[4][0].called
        }
    }

    fun score(draw: Int): Int {
        return data.flatten()
            .filterNot { it.called }
            .sumOf { it.number } * draw
    }
}

data class Cell(
    val number: Int,
    var called: Boolean = false
) {

    fun call(draw: Int): Boolean {
        if (draw == number) {
            called = true
            return true
        }
        return false
    }
}
