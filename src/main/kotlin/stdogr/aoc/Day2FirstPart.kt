package stdogr.aoc

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
            else -> throw IllegalArgumentException("Received broken command: [$command]!")
        }
    }
    return horizontal * depth
}
