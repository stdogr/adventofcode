package stdogr.aoc2022

import java.util.*

class Day5 : Task<String>("5_supply_stacks") {

    override fun partOne(input: String): String {
        val (moves, stacks) = parseMovesAndStacks(input)

        moves.forEach {
            val from = stacks[it.from]!!
            val to = stacks[it.to]!!
            repeat(it.amount) {
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
        val (moves, stacks) = parseMovesAndStacks(input)

        moves.forEach { move ->
            val from = stacks[move.from]!!
            val to = stacks[move.to]!!
            (1..move.amount)
                .map {
                    from.pop()
                }
                .reversed()
                .forEach {
                    to.push(it)
                }
        }

        return String(stacks.values
            .mapNotNull { if (it.isNotEmpty()) it.pop() else null }
            .toCharArray()
        )
    }

    private fun parseMovesAndStacks(input: String): Pair<List<Move>, Map<Int, Stack<Char>>> {
        val stackSetup = mutableMapOf<Int, MutableList<Char>>()
        val moves = mutableListOf<Move>()
        input.lines()
            .filterNot { it.startsWith(" 1") || it.isBlank() }
            .forEach { line ->
                if (line.startsWith("move")) {
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
        val stacks = mutableMapOf<Int, Stack<Char>>()
        stackSetup.forEach { (stackNumber, letters) ->
            if (!stacks.containsKey(stackNumber)) {
                stacks[stackNumber] = Stack()
            }
            letters.reversed().forEach {
                stacks[stackNumber]!!.push(it)
            }
        }
        return Pair(moves, stacks)
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