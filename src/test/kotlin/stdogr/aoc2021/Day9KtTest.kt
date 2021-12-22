package stdogr.aoc2021

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day9KtTest {

    @Test
    fun `sum risk levels`() {
        // given
        val data = """2199943210
3987894921
9856789892
8767896789
9899965678
"""

        // when
        val result = sumRiskLevels(data)

        // then
        Assertions.assertThat(result).isEqualTo(15)
    }

    @Test
    fun `sum risk levels edges`() {
        // given
        val data = """2199943210
3987894921
2856789892
8767896789
9899965676
"""

        // when
        val result = sumRiskLevels(data)

        // then
        Assertions.assertThat(result).isEqualTo(25)
    }

    @Test
    fun `sum risk levels different length`() {
        // given
        val data = """21999432109
39878949219
98567898929
87678967899
98999656789
"""

        // when
        val result = sumRiskLevels(data)

        // then
        Assertions.assertThat(result).isEqualTo(15)
    }
}
