package stdogr.aoc2021

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day5KtTest {

    @Test
    fun `find dangerous vents`() {
        // given
        val data = """0,9 -> 5,9
8,0 -> 0,8
9,4 -> 3,4
2,2 -> 2,1
7,0 -> 7,4
6,4 -> 2,0
0,9 -> 2,9
3,4 -> 1,4
0,0 -> 8,8
5,5 -> 8,2
"""

        // when
        val result = findDangerousVents(data.lines())

        // then
        Assertions.assertThat(result).isEqualTo(5)
    }

    @Test
    fun `find dangerous vents with diagonals`() {
        // given
        val data = """0,9 -> 5,9
8,0 -> 0,8
9,4 -> 3,4
2,2 -> 2,1
7,0 -> 7,4
6,4 -> 2,0
0,9 -> 2,9
3,4 -> 1,4
0,0 -> 8,8
5,5 -> 8,2
"""

        // when
        val result = findDangerousVentsWithDiagonals(data.lines())

        // then
        Assertions.assertThat(result).isEqualTo(12)
    }
}
