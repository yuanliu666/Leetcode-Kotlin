/**
 * Given the head of a linked list, rotate the list to the right by k places.
 */
class Solution_rotate_list {

    // T:O(n) S:O(1)
    fun rotateRight(head: ListNode?, k: Int): ListNode? {
        if (head?.next == null) return head

        val p = getListLenAndTail(head)
        val n = p.first
        val tail = p.second
        val x = k % n
        if (x == 0) return head

        val d = ListNode(-1)
        d.next = head
        var h: ListNode = d
        for (i in 0 until n - x) {
            h = h.next!!
        }
        val next = h.next!!
        h.next = null
        tail.next = d.next
        d.next = next

        return d.next
    }

    private fun getListLenAndTail(head: ListNode): Pair<Int, ListNode> {
        var cnt = 0
        var n: ListNode? = head
        var last: ListNode = head
        while (n != null) {
            cnt++
            last = n
            n = n.next
        }
        return Pair(cnt, last)
    }
}