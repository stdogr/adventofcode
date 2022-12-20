package stdogr.aoc2022

import kotlin.math.floor

class Day11 : Task<Int>("11_monkey_in_the_middle") {

    override fun partOne(input: String): Int {

        val monkeys = input.lines()
            .chunked(7)
            .map { Monkey(it) }
            .associateBy { it.name }

        repeat(20) {
            monkeys.forEach { (_, monkey) ->
                monkey.inspectItems(monkeys)
            }
        }

        val sorted = monkeys.map { it.value.inspections }
            .sortedDescending()

        return sorted[0] * sorted[1]
    }

    override fun partTwo(input: String): Int {
        TODO()
    }

    data class Monkey(val setup: List<String>) {
        val name = numberRegex.find(setup[0])!!.groups[1]!!.value.toInt()

        var inspections = 0

        private var items = itemRegex.find(setup[1])!!.groups[1]!!.value
            .split(", ")
            .map { it.toInt() }
            .toMutableList()

        private val operation = operations()
        private val test = numberRegex.find(setup[3])!!.groups[1]!!.value.toInt()
        private val ifTrue = numberRegex.find(setup[4])!!.groups[1]!!.value.toInt()
        private val ifFalse = numberRegex.find(setup[5])!!.groups[1]!!.value.toInt()

        private fun operations(): (Int) -> Int {
            val find = operationRegex.find(setup[2])!!
            val operator = find.groups[1]!!.value
            val value = find.groups[2]!!.value

            return if (operator == "*" && value == "old") {
                { old: Int -> old * old }
            } else if (operator == "*") {
                { old: Int -> old * value.toInt() }
            } else {
                { old: Int -> old + value.toInt() }
            }
        }

        fun inspectItems(monkeys: Map<Int, Monkey>) {
            items.forEach {
                val inspected = floor(operation.invoke(it).toDouble() / 3.0).toInt()
                if (inspected % test == 0) {
                    monkeys[ifTrue]!!.catch(inspected)
                } else {
                    monkeys[ifFalse]!!.catch(inspected)
                }
                inspections += 1
            }
            items = mutableListOf()
        }

        private fun catch(item: Int) {
            items.add(item)
        }

        companion object {
            private val numberRegex = "([0-9]+)".toRegex()
            private val itemRegex = ": ([0-9, ]+)".toRegex()
            private val operationRegex = " {2}Operation: new = old ([*+]) ([0-9|old]+)".toRegex()

        }
    }
}