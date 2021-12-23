package stdogr.aoc2021

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day12KtTest {

    @Test
    fun `count paths small example`() {
        // given
        val data = """start-A
start-b
A-c
A-b
b-d
A-end
b-end
"""

        // when
        val result = countPaths(data)

        // then
        Assertions.assertThat(result).isEqualTo(10)
    }

    @Test
    fun `count paths medium example`() {
        // given
        val data = """dc-end
HN-start
start-kj
dc-start
dc-HN
LN-dc
HN-end
kj-sa
kj-HN
kj-dc
"""

        // when
        val result = countPaths(data)

        // then
        Assertions.assertThat(result).isEqualTo(19)
    }

    @Test
    fun `count paths large example`() {
        // given
        val data = """fs-end
he-DX
fs-he
start-DX
pj-DX
end-zg
zg-sl
zg-pj
pj-he
RW-he
fs-DX
pj-RW
zg-RW
start-pj
he-WI
zg-he
pj-fs
start-RW
"""

        // when
        val result = countPaths(data)

        // then
        Assertions.assertThat(result).isEqualTo(226)
    }
}
