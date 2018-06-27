import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import utils.ListNode
import utils.isTreeEqualTo

class ConvertSortedListToBinarySearchTreeTest {

    @Test
    fun testSortedListToBst() {
        val s = Solution_convert_sorted_list_to_binary_search_tree()
        val head = ListNode(1)
        head.next = ListNode(2)
        head.next?.next = ListNode(3)
        val ret1 = s.sortedListToBST(head)
        val ret2 = s.sortedListToBSTRecur(head)
        assert(ret1?.isTreeEqualTo(ret2) == true)
        assertThat(ret1?.value, CoreMatchers.equalTo(2))
        assertThat(ret1?.left?.value, CoreMatchers.equalTo(1))
        assertThat(ret1?.right?.value, CoreMatchers.equalTo(3))
    }
}