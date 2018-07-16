import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class CoinChangeTest {

    @Test
    fun testMinCoins() {
        val s = Solution_coin_change()
        assertThat(s.minCoins(arrayOf(1, 2, 5), 11), CoreMatchers.equalTo(3))
        assertThat(s.minCoins(arrayOf(1, 3, 4, 5), 7), CoreMatchers.equalTo(2))
        assertThat(s.minCoins(arrayOf(3, 4, 5), 1), CoreMatchers.equalTo(-1))
    }
}