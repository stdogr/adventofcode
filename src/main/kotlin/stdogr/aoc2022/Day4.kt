package stdogr.aoc2022

class Day4 : Task<Int>("4_camp_cleanup") {

    override fun partOne(input: String): Int {
        return input.lines()
            .mapNotNull { elves ->
                val (firstElf, secondElf) = elves.split(",")
                val (firstStart, firstEnd) = firstElf.split("-").map { it.toInt() }
                val firstSection = (firstStart..firstEnd).toList()

                val (secondStart, secondEnd) = secondElf.split("-").map { it.toInt() }
                val secondSection = (secondStart..secondEnd).toList()

                if (firstSection.containsAll(secondSection) || secondSection.containsAll(firstSection)) {
                    1
                } else null
            }
            .sum()
    }

    override fun partTwo(input: String): Int {
        return input.lines()
            .mapNotNull { elves ->
                val (firstElf, secondElf) = elves.split(",")
                val (firstStart, firstEnd) = firstElf.split("-").map { it.toInt() }
                val firstSection = (firstStart..firstEnd).toList()

                val (secondStart, secondEnd) = secondElf.split("-").map { it.toInt() }
                val secondSection = (secondStart..secondEnd).toList()

                if (firstSection.any { secondSection.contains(it) } || secondSection.any { firstSection.contains(it) }) {
                    1
                } else null
            }
            .sum()
    }
}