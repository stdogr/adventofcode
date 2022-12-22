package stdogr.aoc2022

class Day12 : Task<Int>("12_hill_climbing") {
    override fun partOne(input: String): Int {
        var id = 0

        val nodes = input.lines()
            .flatMapIndexed { rowIndex, line ->
                line.mapIndexed { columnIndex, letter ->
                    LetterNode(columnIndex, rowIndex, letter, id++)
                }
            }

        val edges = nodes.flatMap { letterNode ->
            val maxX = nodes.maxOf { it.x }
            val maxY = nodes.maxOf { it.y }

            listOfNotNull(
                if (letterNode.x > 0) letterNode.x - 1 to letterNode.y else null,
                if (letterNode.x < maxX) letterNode.x + 1 to letterNode.y else null,
                if (letterNode.y > 0) letterNode.x to letterNode.y - 1 else null,
                if (letterNode.y < maxY) letterNode.x to letterNode.y + 1 else null,
            ).mapNotNull { (x, y) ->
                val neighbor = nodes.find { it.y == y && it.x == x }!!
                if (neighbor.score < letterNode.score ||
                    neighbor.score == letterNode.score ||
                    neighbor.score == letterNode.score + 1
                ) {
                    letterNode.id to neighbor.id
                } else null
            }
        }

        dijkstra(nodes, edges)

        return nodes.find { it.letter == 'E' }!!.distance
    }

    private fun dijkstra(
        nodes: List<LetterNode>,
        edges: List<Pair<Int, Int>>
    ) {
        val unvisited = mutableListOf<LetterNode>()
        unvisited.addAll(nodes)

        while (unvisited.isNotEmpty()) {
            val node = unvisited.minByOrNull { it.distance }!!
            unvisited.remove(node)
            edges.filter { it.first == node.id }
                .forEach { (_, adjacentId) ->
                    val adjacentNode = nodes.find { it.id == adjacentId }!!
                    if (adjacentNode.distance > node.distance + 1) {
                        adjacentNode.distance = node.distance + 1
                    }
                }
        }
    }

    override fun partTwo(input: String): Int {
        TODO()
    }


    data class LetterNode(
        val x: Int,
        val y: Int,
        val letter: Char,
        val id: Int,
    ) {

        val score: Int = calculateScore()
        var distance = if (letter == 'S') 0 else Int.MAX_VALUE

        private fun calculateScore(): Int {
            return when (letter) {
                'S' -> 1
                'E' -> 26
                else -> heights[letter]!!
            }
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
