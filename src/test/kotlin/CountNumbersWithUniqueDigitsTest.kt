import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class CountNumbersWithUniqueDigitsTest {

    @Test
    fun testCountNumbersWithUniqueDigits() {
        val s = Solution_count_numbers_with_unique_digits()
        assertThat(s.countNumbersWithUniqueDigits(10), CoreMatchers.equalTo(8877691))
    }
}