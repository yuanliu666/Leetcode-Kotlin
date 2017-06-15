/**
 * You are given two linked lists representing two non-negative numbers.
 * The most significant digit comes first and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 *
 * Example:
 *
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7

 * Definition for singly-linked list.
 * class ListNode(object):
 *     def __init__(self, x):
 *         self.val = x
 *         self.next = None
 */

import utils.ListNode
import java.util.*

class Solution_add_two_numbers_ii {

    // T:O(m+n) S:O(m+n)
    fun addTwoNumbers(a: ListNode, b: ListNode): ListNode {
        val dummy = ListNode()

        // get values
        // because a and b are val, cannot use directly
        val aStk = Stack<Int>()
        aStk.push(a.value)
        val bStk = Stack<Int>()
        bStk.push(b.value)
        var pa: ListNode? = a
        while (pa?.next != null) {
            pa = pa.next
            aStk.push(pa?.value)
        }
        var pb: ListNode? = b
        while (pb?.next != null) {
            pb = pb.next
            bStk.push(pb?.value)
        }

        // add values
        var cur: Int
        var carry = 0
        while (aStk.isNotEmpty() || bStk.isNotEmpty()) {
            cur = carry
            if (aStk.isNotEmpty())
                cur += aStk.pop()
            if (bStk.isNotEmpty())
                cur += bStk.pop()
            carry = cur / 10
            // insert node
            val temp = ListNode(cur % 10)
            temp.next = dummy.next
            dummy.next = temp
        }
        if (carry > 0) {
            val temp = ListNode(carry)
            temp.next = dummy.next
            dummy.next = temp
        }

        return dummy.next!!
    }
}

fun main(args: Array<String>) {
    val s = Solution_add_two_numbers_ii()
    println(s.addTwoNumbers(ListNode.quickList(arrayListOf(7, 2, 4, 3)), ListNode.quickList(arrayListOf(5, 6, 4))))
}
