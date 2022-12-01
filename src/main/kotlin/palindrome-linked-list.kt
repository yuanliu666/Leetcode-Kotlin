/**
 * Given the head of a singly linked list, return true if it is a palindrome or false otherwise.
 */
class Solution_palindrome_linked_list {

    // Using O(n) space is trivial. This solution reverses first half and compare.
    // T:O(n) S:O(1)
    fun isPalindrome(head: ListNode?): Boolean {
        if (head?.next == null) return true
        var cnt = 0
        var cur = head
        while (cur != null) {
            cnt++
            cur = cur.next
        }

        val dummy = ListNode(-1)
        dummy.next = head
        // For a half list of 3, just need to do 2 operations to reverse it
        for (i in 0 until (cnt / 2) - 1) {
            val next = head.next
            head.next = next?.next
            next?.next = dummy.next
            dummy.next = next
        }
        var h = head.next
        if (cnt % 2 == 1) h = h?.next
        var p = dummy.next
        while (h != null) {
            if (p?.`val` != h.`val`) return false
            p = p.next
            h = h.next
        }
        return true
    }
}