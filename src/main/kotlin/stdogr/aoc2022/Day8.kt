package stdogr.aoc2022

class Day8 : Task<Int>("8_treetop_tree_house") {

    override fun partOne(input: String): Int {
        val trees = input.lines()
            .map { line ->
                line.toCharArray().map { it.digitToInt() }.toList()
            }

        val maxRow = trees.size - 1
        val maxColumn = trees.first().size - 1

        var visible = 0

        trees.forEachIndexed { rowIndex, treeRow ->
            treeRow.forEachIndexed { columnIndex, tree ->
                // is outer tree
                if (rowIndex == 0 || rowIndex == maxRow || columnIndex == 0 || columnIndex == maxColumn) {
                    visible += 1
                } else if (
                    (0 until columnIndex).map { trees[rowIndex][it] }.all { it < tree } || // all smaller  west
                    (columnIndex + 1..maxColumn).map { trees[rowIndex][it] }.all { it < tree } || // all smaller east
                    (0 until rowIndex).map { trees[it][columnIndex] }.all { it < tree } || // all smaller north
                    (rowIndex + 1..maxRow).map { trees[it][columnIndex] }.all { it < tree }  // all smaller south
                ) {
                    visible += 1
                }
            }
        }

        return visible
    }

    override fun partTwo(input: String): Int {
        val trees = input.lines()
            .map { line ->
                line.toCharArray().map { it.digitToInt() }.toList()
            }

        val maxRow = trees.size - 1
        val maxColumn = trees.first().size - 1

        var maxScenicScore = 0

        trees.forEachIndexed { rowIndex, treeRow ->
            treeRow.forEachIndexed { columnIndex, tree ->
                if (columnIndex in 1 until maxColumn && rowIndex in 1 until maxRow) {
                    val west = row((0 until columnIndex).reversed(), trees, rowIndex, tree)
                    val east = row(columnIndex + 1..maxColumn, trees, rowIndex, tree)
                    val north = column((0 until rowIndex).reversed(), trees, columnIndex, tree)
                    val south = column(rowIndex + 1..maxRow, trees, columnIndex, tree)

                    val scenicScore = west * east * north * south
                    if (maxScenicScore < scenicScore) maxScenicScore = scenicScore
                }
            }
        }

        return maxScenicScore
    }

    private fun column(
        indexes: IntProgression,
        trees: List<List<Int>>,
        columnIndex: Int,
        tree: Int
    ): Int {
        var scenicScore = 0
        indexes.forEach {
            if (trees[it][columnIndex] >= tree) {
                return scenicScore + 1
            } else {
                scenicScore += 1
            }
        }
        return scenicScore
    }

    private fun row(
        indexes: IntProgression,
        trees: List<List<Int>>,
        rowIndex: Int,
        tree: Int
    ): Int {
        var scenicScore = 0
        indexes.forEach {
            if (trees[rowIndex][it] >= tree) {
                return scenicScore + 1
            } else {
                scenicScore += 1
            }
        }
        return scenicScore
    }
}