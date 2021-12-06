package stdogr.aoc2021

import stdogr.aoc2021.util.loadResourceLines

fun main() {
    val commands = loadResourceLines("2021/day2.txt")
    val resultPart1 = processCommands(commands)
    println("part 1: [$resultPart1]")

    val resultPart2 = processCommandsWithAim(commands)
    println("part 2: [$resultPart2]")
}

fun processCommands(commands: List<String>): Int {
    var horizontal = 0
    var depth = 0
    commands.forEach {
        val (command, numberString) = it.split(" ")
        val number = numberString.toInt()
        when (command) {
            "forward" -> horizontal += number
            "up" -> depth -= number
            "down" -> depth += number
            else -> throw IllegalArgumentException("Received illegal command: [$command]!")
        }
    }
    return horizontal * depth
}

fun processCommandsWithAim(commands: List<String>): Int {
    var horizontal = 0
    var depth = 0
    var aim = 0
    commands.forEach {
        val (command, numberString) = it.split(" ")
        val number = numberString.toInt()
        when (command) {
            "forward" -> {
                horizontal += number
                depth += aim * number
            }
            "up" -> {
                aim -= number
            }
            "down" -> {
                aim += number
            }
            else -> throw IllegalArgumentException("Received illegal command: [$command]!")
        }
    }
    return horizontal * depth
}
