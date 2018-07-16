import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class CoinChange2Test {

    @Test
    fun testChange() {
        val s = Solution_coin_change_2()
        assertThat(s.change(5, intArrayOf(1, 2, 5)), CoreMatchers.equalTo(4))
        assertThat(s.change(3, intArrayOf(2)), CoreMatchers.equalTo(0))
        assertThat(s.change(10, intArrayOf(10)), CoreMatchers.equalTo(1))
    }
}