package stdogr.aoc2021

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day11KtTest {

    @Test
    fun `count flashes after 2 steps`() {
        // given
        val data = """5483143223
2745854711
5264556173
6141336146
6357385478
4167524645
2176841721
6882881134
4846848554
5283751526
"""

        // when
        val result = countFlashes(data, 2)

        // then
        Assertions.assertThat(result).isEqualTo(35)
    }

    @Test
    fun `count flashes after 10 steps`() {
        // given
        val data = """5483143223
2745854711
5264556173
6141336146
6357385478
4167524645
2176841721
6882881134
4846848554
5283751526
"""

        // when
        val result = countFlashes(data, 10)

        // then
        Assertions.assertThat(result).isEqualTo(204)
    }

    @Test
    fun `count flashes after 100 steps`() {
        // given
        val data = """5483143223
2745854711
5264556173
6141336146
6357385478
4167524645
2176841721
6882881134
4846848554
5283751526
"""

        // when
        val result = countFlashes(data, 100)

        // then
        Assertions.assertThat(result).isEqualTo(1656)
    }

    @Test
    fun `find step of simultaneous flash`() {
        // given
        val data = """5483143223
2745854711
5264556173
6141336146
6357385478
4167524645
2176841721
6882881134
4846848554
5283751526
"""

        // when
        val result = simultaneousFlash(data)

        // then
        Assertions.assertThat(result).isEqualTo(195)
    }
}
