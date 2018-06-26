import org.junit.Test

class CombinationsTest {

    @Test
    fun testCombine() {
        val s = Solution_combinations()
        assert(s.combine(4, 2).toSet() ==
                setOf(listOf(2, 4), listOf(3, 4), listOf(2, 3), listOf(1, 2), listOf(1, 3), listOf(1, 4)))
        assert(s.combine2(4, 2).toSet() ==
                setOf(listOf(2, 4), listOf(3, 4), listOf(2, 3), listOf(1, 2), listOf(1, 3), listOf(1, 4)))
    }
}