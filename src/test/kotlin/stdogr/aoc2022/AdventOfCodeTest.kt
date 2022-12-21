package stdogr.aoc2022

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import stdogr.util.loadResource
import java.util.stream.Stream

class AdventOfCodeTest {

    @ParameterizedTest
    @MethodSource("days")
    fun <T> `validate day`(day: Task<T>, expectedResultPartOne: T, expectedResultPartTwo: T) {
        val input = loadResource("2022/${day.filePrefix}_example.txt")
        assertThat(day.partOne(input)).isEqualTo(expectedResultPartOne)
        assertThat(day.partTwo(input)).isEqualTo(expectedResultPartTwo)
    }

    companion object {
        @JvmStatic
        fun days(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(Day1(), 24000, 41000),
                Arguments.of(Day2(), 15, 12),
                Arguments.of(Day3(), 157, 70),
                Arguments.of(Day4(), 2, 4),
                Arguments.of(Day5(), "CMZ", "MCD"),
                Arguments.of(Day6(), 10, 29),
                Arguments.of(Day7(), 95437L, 24933642L),
                Arguments.of(Day8(), 21, 8),
                Arguments.of(Day9(), 13, 1),
                Arguments.of(Day10(), 13140, 0),
                Arguments.of(Day11(), 10605L, 2713310158L),
            )
        }
    }
}