import utils.ListNode

/**
 * Given the head of a singly linked list, return the middle node of the linked list.
 * If there are two middle nodes, return the second middle node.
 *
 * The number of nodes in the list is in the range [1, 100].
 * 1 <= Node.val <= 100
 */
class Solution_middle_of_the_linked_list {

    // T:O(n) S:O(1)
    fun middleNode(head: ListNode?): ListNode? {
        var slow = head
        var fast = head
        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
        }
        return slow
    }
}