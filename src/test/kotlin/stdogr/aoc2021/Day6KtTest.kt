package stdogr.aoc2021

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day6KtTest {

    @Test
    fun `lantern fish after 80 days`() {
        // given
        val data = """3,4,3,1,2
"""

        // when
        val result = lanternFish(data, 80)

        // then
        Assertions.assertThat(result).isEqualTo(5934)
    }

    @Test
    fun `lantern fish after 256 days`() {
        // given
        val data = """3,4,3,1,2
"""

        // when
        val result = lanternFish(data, 256)

        // then
        Assertions.assertThat(result).isEqualTo(26984457539)
    }
}
