import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class CoinPathTest {

    @Test
    fun testGetMinCoins() {
        val s = Solution_coin_path()
        assertThat(s.getMinCoins(arrayOf(1, 2, 4, -1, 2), 2), CoreMatchers.equalTo(listOf(1, 3, 5)))
        assertThat(s.getMinCoins(arrayOf(1, 2, 4, -1, 2), 1), CoreMatchers.equalTo(listOf()))
    }
}