class Solution_remove_nth_node_from_end_of_list {

    // T:O(n) S:O(1)
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        var p = head
        for (i in 0 until n) {
            p = p?.next
        }
        // if n == size
        if (p == null) return head?.next
        // We want the node before the node to be removed to do remove operation
        var q = head
        while (p?.next != null) {
            q = q?.next
            p = p.next
        }
        q?.next = q?.next?.next
        return head
    }
}