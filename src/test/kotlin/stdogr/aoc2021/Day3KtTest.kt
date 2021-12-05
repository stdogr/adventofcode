package stdogr.aoc2021

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day3KtTest {

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
        val gammaAndEpsilonDiagnostic = GammaAndEpsilonDiagnostic(bits)
        val bitString = gammaAndEpsilonDiagnostic.findGamma()
        val result = convertToInt(bitString)

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
        val gammaAndEpsilonDiagnostic = GammaAndEpsilonDiagnostic(bits)
        val bitString = gammaAndEpsilonDiagnostic.findEpsilon()
        val result = convertToInt(bitString)

        // then
        Assertions.assertThat(result).isEqualTo(9)
    }

    @Test
    fun `find product of gamma and epsilon`() {
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
        val result = findProductOfGammaAndEpsilon(bits)

        // then
        Assertions.assertThat(result).isEqualTo(198)
    }

    @Test
    fun `find oxygen generator rating`() {
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
        val result = findOxygenGeneratorRating(bits)

        // then
        Assertions.assertThat(result).isEqualTo(23)
    }

    @Test
    fun `find oxygen scrubber rating`() {
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
        val result = findOxygenScrubberRating(bits)

        // then
        Assertions.assertThat(result).isEqualTo(10)
    }

    @Test
    fun `find life support rating`() {
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
        val result = findLifeSupportRating(bits)

        // then
        Assertions.assertThat(result).isEqualTo(230)
    }
}
