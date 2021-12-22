package stdogr.aoc2021

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.math.exp

class Day10KtTest {

    @Test
    fun `score corrupted`() {
        // given
        val data = """[({(<(())[]>[[{[]{<()<>>
[(()[<>])]({[<{<<[]>>(
{([(<{}[<>[]}>{[]{[(<()>
(((({<>}<{<{<>}{[]{[]{}
[[<[([]))<([[{}[[()]]]
[{[{({}]{}}([{[{{{}}([]
{<[[]]>}<{[{[{[]{()[[[]
[<(<(<(<{}))><([]([]()
<{([([[(<>()){}]>(<<{{
<{([{{}}[<[[[<>{}]]]>[]]
"""

        // when
        val result = scoreCorrupted(data)

        // then
        Assertions.assertThat(result).isEqualTo(26397)
    }

    @Test
    fun `score incomplete`() {
        // given
        val data = """[({(<(())[]>[[{[]{<()<>>
[(()[<>])]({[<{<<[]>>(
{([(<{}[<>[]}>{[]{[(<()>
(((({<>}<{<{<>}{[]{[]{}
[[<[([]))<([[{}[[()]]]
[{[{({}]{}}([{[{{{}}([]
{<[[]]>}<{[{[{[]{()[[[]
[<(<(<(<{}))><([]([]()
<{([([[(<>()){}]>(<<{{
<{([{{}}[<[[[<>{}]]]>[]]
"""

        // when
        val result = scoreIncomplete(data)

        // then
        Assertions.assertThat(result).isEqualTo(288957)
    }

    @ParameterizedTest
    @CsvSource(
        "[({(<(())[]>[[{[]{<()<>>,288957",
        "[(()[<>])]({[<{<<[]>>(,5566",
        "(((({<>}<{<{<>}{[]{[]{}, 1480781",
        "{<[[]]>}<{[{[{[]{()[[[],995444",
        "<{([{{}}[<[[[<>{}]]]>[]],294",
    )
    fun `score incomplete`(input: String, expected: Int) {
        // when
        val result = scoreIncomplete(input)

        // then
        Assertions.assertThat(result).isEqualTo(expected)
    }
}
