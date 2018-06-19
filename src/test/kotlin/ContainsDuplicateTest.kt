import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class ContainsDuplicateTest {

    @Test
    fun testContainsDuplicate() {
        val s = Solution_contains_duplicate()
        assertThat(s.containsDuplicate(intArrayOf(1, 2, 3, 1)), CoreMatchers.equalTo(true))
        assertThat(s.containsDuplicate(intArrayOf(1, 2, 3, 4)), CoreMatchers.equalTo(false))
        assertThat(s.containsDuplicate(intArrayOf(1, 1, 1, 3, 3, 4, 3, 2, 4, 2)), CoreMatchers.equalTo(true))
    }
}