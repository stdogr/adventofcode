package stdogr.aoc2021

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day2KtTest {

    @Test
    fun `process commands`() {
        // given
        val commands = listOf(
            "forward 5",
            "down 5",
            "forward 8",
            "up 3",
            "down 8",
            "forward 2",
        )

        // when
        val result = processCommands(commands)

        // then
        Assertions.assertThat(result).isEqualTo(150)
    }

    @Test
    fun `process commands with aim`() {
        // given
        val commands = listOf(
            "forward 5",
            "down 5",
            "forward 8",
            "up 3",
            "down 8",
            "forward 2",
        )

        // when
        val result = processCommandsWithAim(commands)

        // then
        Assertions.assertThat(result).isEqualTo(900)
    }
}
