package stdogr.aoc2022

class Day3 : Task<Int>("3_rucksack_organization") {

    private val lowerCaseScores = (1..26).toList()
    private val upperCaseScores = (27..52).toList()
    private val scores = ('a'..'z')
        .toList()
        .mapIndexed { index, char ->
            char to lowerCaseScores[index]
        }
        .toMap() + ('A'..'Z').toList()
        .mapIndexed { index, char ->
            char to upperCaseScores[index]
        }
        .toMap()

    override fun partOne(input: String): Int {
        return input.lines()
            .sumOf { rucksack ->
                scores[rucksack.toCharArray(0, rucksack.length / 2)
                    .toSet()
                    .intersect(rucksack.toCharArray(rucksack.length / 2, rucksack.length).toSet())
                    .first()
                ]!!
            }
    }

    override fun partTwo(input: String): Int {
        return input.lines()
            .chunked(3)
            .sumOf { rucksacks ->
                scores[rucksacks[0].toCharArray()
                    .toSet()
                    .intersect(rucksacks[1].toCharArray().toSet())
                    .intersect(rucksacks[2].toCharArray().toSet())
                    .first()]!!
            }
    }
}