import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class CombinationSumIITest {

    @Test
    fun testGetCombinationSumSet() {
        val s = Solution_combination_sum_ii()
        assertThat(s.getCombinationSumSet(arrayOf(10, 1, 2, 7, 6, 1, 5), 8),
                CoreMatchers.equalTo(setOf(listOf(1, 7), listOf(1, 2, 5), listOf(2, 6), listOf(1, 1, 6))))
    }
}