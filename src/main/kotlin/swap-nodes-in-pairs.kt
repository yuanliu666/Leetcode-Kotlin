/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
 */
class Solution_swap_nodes_in_pairs {

    // T:O(n) S:O(1)
    fun swapPairs(head: ListNode?): ListNode? {
        // If less than 2 nodes, no swap
        if (head?.next == null) return head
        val dummy = ListNode(-1)
        dummy.next = head
        var h: ListNode = dummy
        // Swap 2 nodes each time
        while (h.next?.next != null) {
            val n1 = h.next
            val n2 = n1!!.next
            val n3 = n2!!.next
            h.next = n2
            n1.next = n3
            n2.next = n1
            h = n1
        }
        return dummy.next
    }
}