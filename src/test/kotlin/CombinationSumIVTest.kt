import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class CombinationSumIVTest {

    @Test
    fun testCombinationSum4() {
        val s = Solution_combination_sum_iv()
        assertThat(s.combinationSum4(intArrayOf(1, 2, 3), 4), CoreMatchers.equalTo(7))
        assertThat(s.combinationSum4Extra(intArrayOf(1, 2, 3), 4), CoreMatchers.equalTo(7))
    }
}