package stdogr.aoc2022

class Day7 : Task<Long>("7_no_space_left_on_device") {

    override fun partOne(input: String): Long {
        val cdRegex = "\\$ cd ([a-z]+)".toRegex()
        val dirRegex = "dir ([a-z]+)".toRegex()
        val fileRegex = "([0-9]+) ([a-z|\\.]+)".toRegex()

        val nodes = mutableListOf<Node>()

        var current: Node = Root()
        nodes.add(current)
        input.lines()
            .forEach { line ->
                if (line == "$ cd ..") {
                    current = current.parent
                } else if (cdRegex.matches(line)) {
                    val dirName = cdRegex.find(line)!!.groups[1]!!.value
                    current = current.getChild(dirName)
                } else if (dirRegex.matches(line)) {
                    val dirName = dirRegex.find(line)!!.groups[1]!!.value
                    val directory = Directory(dirName, current)
                    nodes.add(directory)
                    current.addChild(directory)
                } else if (fileRegex.matches(line)) {
                    val find = fileRegex.find(line)!!
                    val fileSize = find.groups[1]!!.value.toLong()
                    val fileName = find.groups[2]!!.value
                    current.addFile(fileName, fileSize)
                }
            }

        val relevantNodes = nodes.filter {
            it.totalSize() <= 100000L
        }

       return relevantNodes.sumOf { it.totalSize() }
    }

    override fun partTwo(input: String): Long {
        TODO()
    }

    abstract class Node {
        abstract val dirName: String
        abstract val parent: Node

        private val files = mutableListOf<Pair<String, Long>>()
        private val children = mutableListOf<Node>()

        fun addChild(child: Node) {
            children.add(child)
        }

        fun getChild(dirName: String): Node {
            return children.find { child -> child.dirName == dirName }!!
        }

        fun addFile(fileName: String, fileSize: Long) {
            files.add(fileName to fileSize)
        }

        fun totalSize(): Long {
            return files.sumOf { it.second } + children.sumOf { it.totalSize() }
        }
    }

    class Root : Node() {
        override val dirName: String = "/"
        override val parent: Node = this

        override fun toString(): String {
            return "Root(dirName='$dirName', parent=ROOT)"
        }
    }

    data class Directory(
        override val dirName: String,
        override val parent: Node
    ) : Node()
}