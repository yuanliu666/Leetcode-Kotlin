import utils.ListNode

/**
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 *
 * The number of nodes in the list is the range [0, 5000].
 * -5000 <= Node.val <= 5000
 */

class Solution_reverse_linked_list {

    // T:O(n) S:O(1)
    fun reverseList(head: ListNode?): ListNode? {
        if (head?.next == null) return head

        val d = ListNode(-1)
        d.next = head
        while (head.next != null) {
            val p: ListNode = head.next!!
            val next: ListNode? = p.next
            head.next = next
            p.next = d.next
            d.next = p
        }
        return d.next
    }
}