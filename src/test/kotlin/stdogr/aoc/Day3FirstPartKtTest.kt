package stdogr.aoc

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day3FirstPartKtTest {

    @Test
    fun `find gamma`() {
        // given
        val bits = listOf(
            "00100",
            "11110",
            "10110",
            "10111",
            "10101",
            "01111",
            "00111",
            "11100",
            "10000",
            "11001",
            "00010",
            "01010",
        )

        // when
        val result = findGamma(bits)

        // then
        Assertions.assertThat(result).isEqualTo(22)
    }


    @Test
    fun `find epsilon`() {
        // given
        val bits = listOf(
            "00100",
            "11110",
            "10110",
            "10111",
            "10101",
            "01111",
            "00111",
            "11100",
            "10000",
            "11001",
            "00010",
            "01010",
        )

        // when
        val result = findEpsilon(bits)

        // then
        Assertions.assertThat(result).isEqualTo(9)
    }
}