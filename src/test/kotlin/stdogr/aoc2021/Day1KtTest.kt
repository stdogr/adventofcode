package stdogr.aoc2021

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day1KtTest {

    @Test
    fun `count number of times values increase`() {
        // given
        val values = listOf(
            199, 200, 208, 210, 200, 207, 240, 269, 260, 263
        )

        // when
        val count = countNumberOfTimesValuesIncrease(values)

        // then
        Assertions.assertThat(count).isEqualTo(7)
    }

    @Test
    fun `count number of times values increase windowed`() {
        // given
        val values = listOf(
            199, 200, 208, 210, 200, 207, 240, 269, 260, 263,
        )

        // when
        val count = countNumberOfTimesValuesIncreaseWindowed(values)

        // then
        Assertions.assertThat(count).isEqualTo(5)
    }
}
