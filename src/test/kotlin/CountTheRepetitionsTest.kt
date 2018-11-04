import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class CountTheRepetitionsTest {

    @Test
    fun testGetMaxRepetitions() {
        val s = Solution_count_the_repetitions()
        assertThat(s.getMaxRepetitions("acb", 4, "ab", 2), CoreMatchers.equalTo(2))
    }
}