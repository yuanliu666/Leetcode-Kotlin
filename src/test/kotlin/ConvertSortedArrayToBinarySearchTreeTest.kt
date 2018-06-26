import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class ConvertSortedArrayToBinarySearchTreeTest {

    @Test
    fun testSortedArrayToBst() {
        val s = Solution_convert_sorted_array_to_binary_search_tree()
        val r = s.sortedArrayToBST(intArrayOf(1, 2, 3))
        assertThat(r?.value, CoreMatchers.equalTo(2))
        assertThat(r?.left?.value, CoreMatchers.equalTo(1))
        assertThat(r?.right?.value, CoreMatchers.equalTo(3))
    }
}