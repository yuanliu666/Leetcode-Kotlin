import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class CountingBitsTest {

    @Test
    fun testCountingBits() {
        val s = Solution_counting_bits()
        assertThat(s.countBits(2), CoreMatchers.equalTo(intArrayOf(0, 1, 1)))
        assertThat(s.countBits(5), CoreMatchers.equalTo(intArrayOf(0, 1, 1, 2, 1, 2)))
    }
}