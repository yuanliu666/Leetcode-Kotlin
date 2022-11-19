import utils.ListNode

/**
 * You are given the heads of two sorted linked lists list1 and list2.
 * Merge the two lists in a one sorted list.
 * The list should be made by splicing together the nodes of the first two lists.
 * Return the head of the merged linked list.
 *
 * The number of nodes in both lists is in the range [0, 50].
 * -100 <= Node.val <= 100
 * Both list1 and list2 are sorted in non-decreasing order.
 */
class Solution_merge_two_sorted_lists {

    // T:O(m+n) S:O(1)
    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        var l1 = list1
        var l2 = list2

        val list3 = ListNode(-1)
        var cur: ListNode? = list3
        while (l1 != null && l2 != null) {
            // Leetcode uses l1.`val` which is just ugly
            val a = l1.value
            val b = l2.value
            if (a < b) {
                // Get node from list 1
                cur?.next = l1
                l1 = l1.next
                cur = cur?.next
                cur?.next = null
            } else {
                // Get node from list 2
                cur?.next = l2
                l2 = l2.next
                cur = cur?.next
                cur?.next = null
            }
        }
        if (l1 != null) {
            cur?.next = l1
        }
        if (l2 != null) {
            cur?.next = l2
        }
        return list3.next
    }
}