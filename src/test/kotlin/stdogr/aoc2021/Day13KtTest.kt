package stdogr.aoc2021

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day13KtTest {

    @Test
    fun `count visible dots after first fold`() {
        // given
        val data = """6,10
0,14
9,10
0,3
10,4
4,11
6,0
6,12
4,1
0,13
10,12
3,4
3,0
8,4
1,10
2,14
8,10
9,0

fold along y=7
fold along x=5
"""

        // when
        val result = countVisibleDotsAfterFirstFold(data)

        // then
        Assertions.assertThat(result).isEqualTo(17)
    }
}
