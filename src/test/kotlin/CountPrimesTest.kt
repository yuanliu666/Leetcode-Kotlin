import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class CountPrimesTest {

    @Test
    fun testCountPrimes() {
        val s = Solution_count_primes()
        assertThat(s.countPrimes(10), CoreMatchers.equalTo(4))
        assertThat(s.countPrimes(499979), CoreMatchers.equalTo(41537))
    }
}