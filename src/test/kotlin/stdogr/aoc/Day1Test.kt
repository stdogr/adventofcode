package stdogr.aoc

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day1Test {

    @Test
    fun `check input includes 7 increased values`() {
        // given
        val values = listOf(
            199, 200, 208, 210, 200, 207, 240, 269, 260, 263
        )

        // when
        val count = countRisingNumbers(values)

        // then
        Assertions.assertThat(count).isEqualTo(7)
    }
}
