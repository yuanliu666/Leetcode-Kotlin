/**
 * You are given two linked lists representing two non-negative numbers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 */

import utils.ListNode

class Solution_add_two_numbers {

    //T:O(n) S:O(1)
    fun addTwoNumbers(a: ListNode, b: ListNode): ListNode {
        val dummy = ListNode()
        var cur: ListNode = dummy
        var pa: ListNode? = a
        var pb: ListNode? = b
        var sum: Int
        var carry = 0
        while (pa != null || pb != null) {
            sum = carry
            if (pa != null) {
                sum += pa.value
                pa = pa.next
            }
            if (pb != null) {
                sum += pb.value
                pb = pb.next
            }
            carry = sum / 10
            cur.next = ListNode(sum % 10)
            cur = cur.next as ListNode
        }
        if (carry > 0) {
            cur.next = ListNode(carry)
        }

        return dummy.next!!
    }
}

fun main(args: Array<String>) {
    println(Solution_add_two_numbers()
            .addTwoNumbers(ListNode.quickList(arrayListOf(2, 4, 3)),
                    ListNode.quickList(arrayListOf(5, 6, 4))))
}
