package stdogr.aoc

import stdogr.aoc.util.FileLoader

fun main() {
    val data = FileLoader().load("2021/day2.txt")
    val commands = data.lines().filter { it.isNotBlank() }
    val result = processCommandsWithAim(commands)
    println(result)
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
