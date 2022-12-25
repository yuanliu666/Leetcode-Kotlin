/**
 * Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
 * k is a positive integer and is less than or equal to the length of the linked list.
 * If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
 * You may not alter the values in the list's nodes, only nodes themselves may be changed.
 */
class Solution_reverse_nodes_in_k_group {

    // T:O(n) S:O(1)
    fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
        if (k == 1 || head?.next == null) return head

        val n = getListLen(head)
        val cnt = n / k
        val d = ListNode(-1)
        d.next = head
        var h: ListNode = d
        // Do a group of reverse operations each time
        for (i in 0 until cnt) {
            val cur: ListNode = h.next!!
            // To reverse length of k, only need k-1 operations
            for (j in 1 until k) {
                val next: ListNode = cur.next!!
                val nextNext = next.next
                val subhead: ListNode = h.next!!

                h.next = next
                next.next = subhead
                cur.next = nextNext
            }
            h = cur
        }

        return d.next
    }

    private fun getListLen(head: ListNode?): Int {
        var h = head
        var cnt = 0
        while (h != null) {
            cnt++
            h = h.next
        }
        return cnt
    }
}