import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class CountOfSmallerNumbersAfterSelfTest {

    @Test
    fun testCountSmaller() {
        val s = Solution_count_of_smaller_numbers_after_self()
        assertThat(s.countSmaller(intArrayOf(5, 2, 6, 1)), CoreMatchers.equalTo(listOf(2, 1, 1, 0)))
        assertThat(s.countSmaller(intArrayOf(5, 2, 6, -1, 1, 8, 6, 2, 5, 0, 8, -5)),
                CoreMatchers.equalTo(listOf(6, 4, 6, 1, 2, 5, 4, 2, 2, 1, 1, 0)))
    }
}