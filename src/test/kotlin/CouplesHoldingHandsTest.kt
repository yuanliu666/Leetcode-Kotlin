import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class CouplesHoldingHandsTest {

    @Test
    fun testMinSwapsCouples() {
        val s = Solution_couples_holding_hands()
        assertThat(s.minSwapsCouples(intArrayOf(0, 2, 1, 3)), CoreMatchers.equalTo(1))
        assertThat(s.minSwapsCouples(intArrayOf(3, 2, 0, 1)), CoreMatchers.equalTo(0))
        assertThat(s.minSwapsCouples(intArrayOf(5, 4, 2, 6, 3, 1, 0, 7)), CoreMatchers.equalTo(2))
    }
}