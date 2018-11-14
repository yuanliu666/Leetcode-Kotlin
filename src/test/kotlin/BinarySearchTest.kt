import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class BinarySearchTest {

    private val s = BinarySearch()

    @Test
    fun testFindExactIndex() {
        assertThat(s.findExactIndex(intArrayOf(1), 1), CoreMatchers.equalTo(0))
        assertThat(s.findExactIndex(intArrayOf(1, 2, 3), 1), CoreMatchers.equalTo(0))
        assertThat(s.findExactIndex(intArrayOf(1, 2, 3), 2), CoreMatchers.equalTo(1))
        assertThat(s.findExactIndex(intArrayOf(1, 2, 3), 3), CoreMatchers.equalTo(2))
    }

    @Test
    fun testFindExactIndexRecur() {
        assertThat(s.findExactIndexRecur(intArrayOf(1), 1), CoreMatchers.equalTo(0))
        assertThat(s.findExactIndexRecur(intArrayOf(1, 2, 3), 1), CoreMatchers.equalTo(0))
        assertThat(s.findExactIndexRecur(intArrayOf(1, 2, 3), 2), CoreMatchers.equalTo(1))
        assertThat(s.findExactIndexRecur(intArrayOf(1, 2, 3), 3), CoreMatchers.equalTo(2))
    }

    @Test
    fun testFindClosestIndex() {
        assertThat(s.findClosestIndex(intArrayOf(1), 0), CoreMatchers.equalTo(0))
        assertThat(s.findClosestIndex(intArrayOf(1, 2, 3), 0), CoreMatchers.equalTo(0))
        assertThat(s.findClosestIndex(intArrayOf(1, 2, 3), 4), CoreMatchers.equalTo(2))
        assertThat(s.findClosestIndex(intArrayOf(1, 4, 7), 2), CoreMatchers.equalTo(0))
        assertThat(s.findClosestIndex(intArrayOf(1, 4, 7), 3), CoreMatchers.equalTo(1))
        assertThat(s.findClosestIndex(intArrayOf(1, 4, 7), 5), CoreMatchers.equalTo(1))
        assertThat(s.findClosestIndex(intArrayOf(1, 4, 7), 6), CoreMatchers.equalTo(2))
    }
}