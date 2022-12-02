/**
 * Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.
 * The first node is considered odd, and the second node is even, and so on.
 * Note that the relative order inside both the even and odd groups should remain as it was in the input.
 * You must solve the problem in O(1) extra space complexity and O(n) time complexity.
 */
class Solution_odd_even_linked_list {

    // T:O(n) S:O(1)
    fun oddEvenList(head: ListNode?): ListNode? {
        val odd = ListNode(-1)
        var p: ListNode? = odd
        val even = ListNode(-1)
        var q: ListNode? = even
        val dummy = ListNode(-1)
        dummy.next = head

        var isOdd = true
        while (dummy.next != null) {
            val cur = dummy.next
            if (isOdd) {
                p?.next = cur
                dummy.next = cur?.next
                cur?.next = null
                p = p?.next
            } else {
                q?.next = cur
                dummy.next = cur?.next
                cur?.next = null
                q = q?.next
            }
            isOdd = !isOdd
        }
        p?.next = even.next
        return odd.next
    }
}