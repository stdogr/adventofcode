package stdogr.aoc2021

import stdogr.aoc2021.util.loadResource

fun main() {
    val data = loadResource("2021/day12.txt")
    val resultPart1 = countPaths(data)
    println("part 1: [$resultPart1]")

    val resultPart2 = countPaths(data)
    println("part 2: [$resultPart2]")
}

fun countPaths(data: String): Int {
    val nodes = parse(data)

    return expand(nodes)
        .filter { it.isValid() }
        .size
}

private fun expand(nodes: Map<String, Node>): Set<Path> {
    val first = nodes.values.find { it.start }!!

    var paths = setOf(Path(first))
    while (true) {
        val newPaths = paths.flatMap {
            val newPaths = it.expand(nodes)
            newPaths
        }.toSet()

        if (newPaths == paths) {
            return paths
        }
        paths = newPaths
    }
}

private fun parse(data: String): Map<String, Node> {
    val edges = data.lines()
        .filterNot { it.isBlank() }
        .map { line ->
            val edge = line.split("-")
            edge.first() to edge.last()
        }

    val nodes = mutableMapOf<String, Node>()

    edges.forEach { (firstName, secondName) ->
        val first = Node(firstName)
        val second = Node(secondName)

        if (!nodes.containsKey(first.name)) {
            nodes[first.name] = first
        }
        nodes[first.name]!!.add(second.name)

        if (!nodes.containsKey(second.name)) {
            nodes[second.name] = second
        }
        nodes[second.name]!!.add(first.name)
    }

    return nodes
}

class Path {

    private val nodes: List<Node>
    private var joker = false

    constructor(start: Node) {
        this.nodes = listOf(start)
    }

    constructor(nodes: List<Node>) {
        this.nodes = nodes
    }

    fun expand(nodes: Map<String, Node>): Set<Path> {
        val last = this.nodes.last()
        if (last.end) {
            return setOf(this)
        }
        val targetNodes = last.targetNodes(nodes)
        val newPaths = targetNodes.mapNotNull { newNode ->
            if (!this.nodes.contains(newNode) || newNode.multipleVisitsAllowed) {
                val path = listOf(*this.nodes.toTypedArray(), newNode)
                Path(path)
            } else {
                null
            }
        }
        return newPaths.toSet()
    }

    fun isValid(): Boolean {
        return nodes.first().start && nodes.last().end
    }

    override fun toString() = "Path:$nodes"
}

data class Node(val name: String) {

    val start = name == "start"
    val end = name == "end"
    val multipleVisitsAllowed = name.uppercase() == name

    private val targetNodeNames = mutableSetOf<String>()

    fun targetNodes(nodeNames: Map<String, Node>) = targetNodeNames.map { nodeNames[it]!! }

    fun add(name: String) {
        targetNodeNames.add(name)
    }

    override fun toString() = name
}
