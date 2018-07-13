import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class CountBinarySubstringsTest {

    @Test
    fun testCountBinarySubstrings() {
        val s = Solution_count_binary_substrings()
        assertThat(s.countBinarySubstrings("00110011"), CoreMatchers.equalTo(6))
        assertThat(s.countBinarySubstrings("10101"), CoreMatchers.equalTo(4))
    }
}