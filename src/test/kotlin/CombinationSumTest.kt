import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class CombinationSumTest {

    @Test
    fun testGetCombinationSet() {
        val s = Solution_combination_sum()
        assertThat(s.getCombinationSet(arrayOf(2, 3, 6, 7), 7),
                CoreMatchers.equalTo(setOf(listOf(7), listOf(2, 2, 3))))
    }
}