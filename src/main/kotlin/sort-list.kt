/**
 * Given the head of a linked list, return the list after sorting it in ascending order.
 * Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
 */
class Solution_sort_list {

    // T:O(nlogn) S:O(logn) with recursion stack
    fun sortList(head: ListNode?): ListNode? {
        if (head?.next == null) return head
        // Split list into half
        // e.g. 1->2->3->4, need s to stop on 2
        var f = head.next
        var s = head
        while (f?.next != null) {
            f = f.next?.next
            s = s?.next
        }
        val h = s?.next
        // Break the chain
        s?.next = null
        // Recursion
        val left = sortList(head)
        val right = sortList(h)
        return merge(left, right)
    }

    // Merge 2 sorted linked list. T:O(m+n) S:O(1)
    private fun merge(l1: ListNode?, l2: ListNode?): ListNode? {
        val d1 = ListNode(-1)
        d1.next = l1
        val d2 = ListNode(-1)
        d2.next = l2
        val d = ListNode(-1)
        var cur = d

        while (d1.next != null && d2.next != null) {
            val n1 = d1.next!!
            val n2 = d2.next!!
            if (n1.`val` < n2.`val`) {
                cur.next = n1
                d1.next = n1.next
                n1.next = null
            } else {
                cur.next = n2
                d2.next = n2.next
                n2.next = null
            }
            cur = cur.next!!
        }
        if (d1.next != null) {
            cur.next = d1.next
        }
        if (d2.next != null) {
            cur.next = d2.next
        }
        return d.next
    }

    // For follow up, there is a bottom-up merge sort that can achieve this.
    // Check https://leetcode.com/problems/sort-list/discuss/46712/Bottom-to-up(not-recurring)-with-o(1)-space-complextity-and-o(nlgn)-time-complextity
}