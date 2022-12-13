package stdogr.aoc2022

import java.util.*

class Day5 : Task<String>("5_supply_stacks") {

    override fun partOne(input: String): String {
        val stackSetup = mutableMapOf<Int, MutableList<Char>>()
        val moves = mutableListOf<Move>()
        var initBlockOver = false
        input.lines()
            .forEach { line ->
                if (line.isBlank()) {
                    initBlockOver = true
                } else if (!line.startsWith(" 1")) {
                    if (initBlockOver) {
                        moves.add(Move(line))
                    } else {
                        line.toCharArray()
                            .toList()
                            .chunked(4)
                            .forEachIndexed { index, chars ->
                                if (!stackSetup.containsKey(index + 1)) {
                                    stackSetup[index + 1] = mutableListOf()
                                }
                                val element = chars[1]
                                if (element.isLetter()) {
                                    stackSetup[index + 1]!!.add(element)
                                }
                            }
                    }
                }
            }
        val stacks = mutableMapOf<Int, Stack<Char>>()
        stackSetup.forEach { (stackNumber, letters) ->
            if (!stacks.containsKey(stackNumber)) {
                stacks[stackNumber] = Stack()
            }
            letters.reversed().forEach {
                stacks[stackNumber]!!.push(it)
            }
        }
        moves.forEach {
            val from = stacks[it.from]!!
            val to = stacks[it.to]!!
            (1..it.amount).forEach {
                val element = from.pop()
                to.push(element)
            }
        }

        return String(stacks.values
            .mapNotNull { if (it.isNotEmpty()) it.pop() else null }
            .toCharArray()
        )
    }

    override fun partTwo(input: String): String {
        TODO("Not yet implemented")
    }

    private data class Move(
        private val line: String,
    ) {
        val amount: Int = amount(line)
        val from: Int = from(line)
        val to: Int = to(line)

        private fun amount(line: String): Int {
            return "move ([\\d]+)".toRegex().find(line)!!
                .groups[1]!!.value.toInt()
        }

        private fun from(line: String): Int {
            return "from ([\\d]+)".toRegex().find(line)!!
                .groups[1]!!.value.toInt()
        }

        private fun to(line: String): Int {
            return "to ([\\d]+)".toRegex().find(line)!!
                .groups[1]!!.value.toInt()
        }
    }
}