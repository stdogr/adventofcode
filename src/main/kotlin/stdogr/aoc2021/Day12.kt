package stdogr.aoc2021

import stdogr.util.loadResource

fun main() {
    val data = loadResource("2021/day12.txt")
    val resultPart1 = countPaths(data)
    println("part 1: [$resultPart1]")

    val resultPart2 = countPaths(data, true)
    println("part 2: [$resultPart2]")
}

fun countPaths(data: String, joker: Boolean = false): Int {
    val nodes = parse(data)
    val start = nodes.find { it.start }!!

    return expand(start, joker).size
}

private fun expand(start: Node, joker: Boolean = false): Set<Path> {
    var paths = setOf(Path(start))
    while (true) {
        val newPaths = paths.flatMap {
            val newPaths = it.expand(joker)
            newPaths
        }.toSet()

        if (newPaths == paths) {
            return paths
        }
        paths = newPaths
    }
}

private fun parse(data: String): Collection<Node> {
    val edges = data.lines()
        .filterNot { it.isBlank() }
        .map { line ->
            val edge = line.split("-")
            edge.first() to edge.last()
        }

    val nameMapping = mutableMapOf<String, MutableList<String>>()

    edges.forEach { (firstName, secondName) ->
        if (!nameMapping.containsKey(firstName)) {
            nameMapping[firstName] = mutableListOf()
        }
        nameMapping[firstName]!!.add(secondName)

        if (!nameMapping.containsKey(secondName)) {
            nameMapping[secondName] = mutableListOf()
        }
        nameMapping[secondName]!!.add(firstName)
    }

    val nodes: Map<String, Node> = nameMapping.keys.associateWith { Node(it) }
    nameMapping.forEach { nodeName, links ->
        links.forEach { linkName ->
            nodes[nodeName]!!.link(nodes[linkName]!!)
        }
    }

    return nodes.values
}

class Path {

    private val nodes: List<Node>
    private val usedJoker: Boolean

    constructor(start: Node) {
        this.nodes = listOf(start)
        this.usedJoker = false
    }

    constructor(nodes: List<Node>, usedJoker: Boolean) {
        this.nodes = nodes
        this.usedJoker = usedJoker
    }

    fun expand(joker: Boolean = false): Set<Path> {
        val last = this.nodes.last()
        if (last.end) {
            return setOf(this)
        }
        val linkedNodes = last.linkedNodes()
        val newPaths = linkedNodes.mapNotNull { newNode ->
            if (newNode.start) {
                null
            } else if (!this.nodes.contains(newNode) || newNode.multipleVisitsAllowed) {
                val path = listOf(*this.nodes.toTypedArray(), newNode)
                Path(path, usedJoker)
            } else if (joker && !usedJoker) {
                val path = listOf(*this.nodes.toTypedArray(), newNode)
                Path(path, true)
            } else {
                null
            }
        }
        return newPaths.toSet()
    }
}

data class Node(val name: String) {

    val start = name == "start"
    val end = name == "end"
    val multipleVisitsAllowed = name.uppercase() == name

    private val linkedNodes = mutableSetOf<Node>()

    fun linkedNodes() = linkedNodes.toSet()

    fun link(other: Node) {
        other.addNode(this)
        this.addNode(other)
    }

    private fun addNode(node: Node) {
        linkedNodes.add(node)
    }
}
