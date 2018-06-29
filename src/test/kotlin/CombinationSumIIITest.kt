import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class CombinationSumIIITest {

    @Test
    fun testGetSets() {
        val s = Solution_combination_sum_iii()
        assertThat(s.getSets(3, 7), CoreMatchers.equalTo(setOf(listOf(1, 2, 4))))
        assertThat(s.getSets(3, 9),
                CoreMatchers.equalTo(setOf(listOf(1, 2, 6), listOf(1, 3, 5), listOf(2, 3, 4))))
    }
}