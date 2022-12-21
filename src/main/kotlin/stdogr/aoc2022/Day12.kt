package stdogr.aoc2022

class Day12 : Task<Int>("12_hill_climbing") {

    override fun partOne(input: String): Int {
        var start: Node? = null
        val matrix = input.lines()
            .mapIndexed { rowIndex, line ->
                line.mapIndexed { columnIndex, letter ->
                    val node = Node(columnIndex, rowIndex, letter)
                    if (node.start) start = node
                    node
                }
            }

        matrix.forEach { row ->
            row.forEach { currentNode ->
                currentNode.addNeighbors(matrix)
            }
        }

        val paths = expand(start!!)

        return paths.minOf { it.length() }
    }

    private fun expand(start: Node): Set<Path> {
        var paths = setOf(Path(start))
        while (true) {
            val newPaths = paths.flatMap { path ->
                val newPaths = path.expand(paths.filter { it.isComplete() }.minOfOrNull { it.length() })
                newPaths
            }.toSet()

            if (newPaths == paths) {
                return paths
            }
            paths = newPaths
        }
    }

    override fun partTwo(input: String): Int {
        TODO()
    }

    class Path {

        private val nodes: List<Node>

        constructor(start: Node) {
            this.nodes = listOf(start)
        }

        constructor(nodes: List<Node>) {
            this.nodes = nodes
        }

        fun expand(shortestCompletePath: Int?): Set<Path> {
            val last = nodes.last()
            return if (last.end) {
                setOf(this)
            } else if (last.getNeighbors().isEmpty()) {
                emptySet()
            } else {
                last.getNeighbors()
                    .mapNotNull { neighbor ->
                        if (this.nodes.contains(neighbor)) {
                            null
                        } else if (shortestCompletePath == null || length() < shortestCompletePath) {
                            Path(listOf(*this.nodes.toTypedArray(), neighbor))
                        } else null
                    }.toSet()
            }
        }

        fun length() = nodes.size - 1 // subtract start node from path length

        fun isComplete() = nodes.last().end
    }

    data class Node(
        val x: Int,
        val y: Int,
        val letter: Char,
    ) {
        val start = letter == 'S'
        val end = letter == 'E'

        private val score = calculateScore()

        private val neighbors = mutableSetOf<Node>()

        fun getNeighbors(): Set<Node> = neighbors

        fun addNeighbors(matrix: List<List<Node>>) {
            val maxX = matrix.first().size - 1
            val maxY = matrix.size - 1

            val left = if (x > 0) x - 1 to y else null
            left?.let {
                val neighbor = matrix[it.second][it.first]
                addNeighbor(neighbor)
            }
            val right = if (x < maxX) x + 1 to y else null
            right?.let {
                val neighbor = matrix[it.second][it.first]
                addNeighbor(neighbor)
            }
            val up = if (y > 0) x to y - 1 else null
            up?.let {
                val neighbor = matrix[it.second][it.first]
                addNeighbor(neighbor)
            }
            val down = if (y < maxY) x to y + 1 else null
            down?.let {
                val neighbor = matrix[it.second][it.first]
                addNeighbor(neighbor)
            }
        }

        private fun addNeighbor(neighbor: Node) {
            if (neighbor.score < this.score ||
                neighbor.score == this.score ||
                neighbor.score == this.score + 1
            ) {
                neighbors.add(neighbor)
            }
        }

        private fun calculateScore(): Int {
            return if (start) {
                1
            } else if (end) {
                26
            } else heights[letter]!!
        }

        companion object {
            private val heightValues = (1..26).toList()
            private val heights = ('a'..'z')
                .toList()
                .mapIndexed { index, char ->
                    char to heightValues[index]
                }
                .toMap()
        }
    }
}