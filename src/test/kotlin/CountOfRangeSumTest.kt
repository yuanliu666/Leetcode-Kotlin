import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class CountOfRangeSumTest {

    @Test
    fun testCountRangeSum() {
        val s = Solution_count_of_range_sum()
        assertThat(s.countRangeSum(intArrayOf(-2, 5, -1), -2, 2), CoreMatchers.equalTo(3))
    }
}