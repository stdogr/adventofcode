package stdogr.aoc2021

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day7KtTest {

    @Test
    fun `fuel crabs cheap`() {
        // given
        val data = """16,1,2,0,4,2,7,1,2,14"""

        // when
        val result = fuelCrabs(data) { it }

        // then
        Assertions.assertThat(result).isEqualTo(37)
    }

    @Test
    fun `fuel crabs cheap with increasing cost`() {
        // given
        val data = """16,1,2,0,4,2,7,1,2,14"""

        // when
        val result = fuelCrabs(data) { position -> (1..position).sumOf { it } }

        // then
        Assertions.assertThat(result).isEqualTo(168)
    }
}
