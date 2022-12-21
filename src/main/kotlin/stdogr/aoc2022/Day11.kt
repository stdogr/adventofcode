package stdogr.aoc2022

import kotlin.math.floor

class Day11 : Task<Long>("11_monkey_in_the_middle") {

    override fun partOne(input: String): Long {
        val monkeys = input.lines()
            .chunked(7)
            .map { Monkey(it) }
            .associateBy { it.name }

        repeat(20) {
            monkeys.forEach { (_, monkey) ->
                monkey.inspectItems(monkeys) {
                    floor(it.toDouble() / 3.0).toLong()
                }
            }
        }

        val sorted = monkeys.map { it.value.inspections }
            .sortedDescending()

        return sorted[0] * sorted[1]
    }

    override fun partTwo(input: String): Long {
        val monkeys = input.lines()
            .chunked(7)
            .map { Monkey(it) }
            .associateBy { it.name }

        val mod = monkeys.values.map { it.test }
            .reduce { a, b -> a * b }

        repeat(10000) {
            monkeys.forEach { (_, monkey) ->
                monkey.inspectItems(monkeys) {
                    it % mod
                }
            }
        }

        val sorted = monkeys.map { it.value.inspections }
            .sortedDescending()

        return sorted[0] * sorted[1]
    }

    data class Monkey(val setup: List<String>) {
        val name = numberRegex.find(setup[0])!!.groups[1]!!.value.toInt()

        var inspections = 0L

        var items = itemRegex.find(setup[1])!!.groups[1]!!.value
            .split(", ")
            .map { it.toLong() }
            .toMutableList()

        private val operation = operations()
        val test = numberRegex.find(setup[3])!!.groups[1]!!.value.toInt()
        private val ifTrue = numberRegex.find(setup[4])!!.groups[1]!!.value.toInt()
        private val ifFalse = numberRegex.find(setup[5])!!.groups[1]!!.value.toInt()

        private fun operations(): (Long) -> Long {
            val find = operationRegex.find(setup[2])!!
            val operator = find.groups[1]!!.value
            val value = find.groups[2]!!.value

            return if (operator == "*" && value == "old") {
                { old: Long -> old * old }
            } else if (operator == "*") {
                { old: Long -> old * value.toLong() }
            } else {
                { old: Long -> old + value.toLong() }
            }
        }

        fun inspectItems(monkeys: Map<Int, Monkey>, reduction: (Long) -> Long) {
            items.forEach {
                val invoke = operation.invoke(it)
                val inspected = reduction.invoke(invoke)
                if (inspected % test == 0L) {
                    monkeys[ifTrue]!!.catch(inspected)
                } else {
                    monkeys[ifFalse]!!.catch(inspected)
                }
                inspections += 1
            }
            items = mutableListOf()
        }

        private fun catch(item: Long) {
            items.add(item)
        }

        companion object {
            private val numberRegex = "([0-9]+)".toRegex()
            private val itemRegex = ": ([0-9, ]+)".toRegex()
            private val operationRegex = " {2}Operation: new = old ([*+]) ([0-9|old]+)".toRegex()

        }
    }
}