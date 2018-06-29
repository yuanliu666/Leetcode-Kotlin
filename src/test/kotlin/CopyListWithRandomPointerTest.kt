import org.hamcrest.CoreMatchers
import org.junit.Assert.*
import org.junit.Test

class CopyListWithRandomPointerTest {

    @Test
    fun testCopyRandomList() {
        val s = Solution_copy_list_with_random_pointer()
        val node1 = Solution_copy_list_with_random_pointer.RandomListNode(1)
        val node2 = Solution_copy_list_with_random_pointer.RandomListNode(2)
        val node3 = Solution_copy_list_with_random_pointer.RandomListNode(3)
        node1.next = node2
        node2.next = node3
        node1.random = node3

        val ret1 = s.copyRandomList(node1)
        assertNotNull(ret1)
        assertNotNull(ret1?.next)
        assertNotNull(ret1?.next?.next)
        assertNull(ret1?.next?.next?.next)
        assertThat(ret1?.value, CoreMatchers.equalTo(1))
        assertThat(ret1?.next?.value, CoreMatchers.equalTo(2))
        assertThat(ret1?.next?.next?.value, CoreMatchers.equalTo(3))
        assertThat(ret1?.random?.value, CoreMatchers.equalTo(3))

        val ret2 = s.copyRandomList2(node1)
        assertNotNull(ret2)
        assertNotNull(ret2?.next)
        assertNotNull(ret2?.next?.next)
        assertNull(ret2?.next?.next?.next)
        assertThat(ret2?.value, CoreMatchers.equalTo(1))
        assertThat(ret2?.next?.value, CoreMatchers.equalTo(2))
        assertThat(ret2?.next?.next?.value, CoreMatchers.equalTo(3))
        assertThat(ret2?.random?.value, CoreMatchers.equalTo(3))
    }
}