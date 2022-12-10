package stdogr.aoc2022

class Day2 : Day<Int>("day2") {
    override fun partOne(input: String): Int {
        val matrix = mapOf(
            "A" to mapOf("X" to 3, "Y" to 6, "Z" to 0),
            "B" to mapOf("X" to 0, "Y" to 3, "Z" to 6),
            "C" to mapOf("X" to 6, "Y" to 0, "Z" to 3),
        )

        val score = mapOf("X" to 1, "Y" to 2, "Z" to 3)

        return input
            .lines()
            .filter { it.isNotBlank() }
            .sumOf {
                val shapes = it.split(" ")

                matrix[shapes[0]]!![shapes[1]]!! + score[shapes[1]]!!
            }
    }

    override fun partTwo(input: String): Int {
        val matrix = mapOf(
            "A" to mapOf("X" to 3, "Y" to 6, "Z" to 0),
            "B" to mapOf("X" to 0, "Y" to 3, "Z" to 6),
            "C" to mapOf("X" to 6, "Y" to 0, "Z" to 3),
        )

        val translate = mapOf(
            "X" to mapOf("A" to "Z", "B" to "X", "C" to "Y"),
            "Y" to mapOf("A" to "X", "B" to "Y", "C" to "Z"),
            "Z" to mapOf("A" to "Y", "B" to "Z", "C" to "X"),
        )

        val score = mapOf("X" to 1, "Y" to 2, "Z" to 3)

        return input
            .lines()
            .filter { it.isNotBlank() }
            .sumOf {
                val shapes = it.split(" ")
                val opponent = shapes[0]
                val command = shapes[1]
                val shape = translate[command]!![opponent]

                matrix[opponent]!![shape]!! + score[shape]!!
            }
    }
}