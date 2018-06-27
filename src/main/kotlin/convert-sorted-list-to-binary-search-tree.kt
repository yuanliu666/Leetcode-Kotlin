/**
 * Given a singly linked list where elements are sorted in ascending order,
 * convert it to a height balanced BST.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree in which
 * the depth of the two subtrees of every node never differ by more than 1.
 *
 * Example:
 *
 * Given the sorted linked list: [-10,-3,0,5,9],
 *
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *
 *      0
 *     / \
 *   -3   9
 *   /   /
 * -10  5
 */

import utils.ListNode
import utils.TreeNode

class Solution_convert_sorted_list_to_binary_search_tree {

    // take advantage of previous solution
    // T:O(n) S:O(n)
    fun sortedListToBST(head: ListNode?): TreeNode? =
            Solution_convert_sorted_array_to_binary_search_tree()
                    .sortedArrayToBST(toSortedArray(head).toIntArray())

    private fun toSortedArray(head: ListNode?): List<Int> {
        val ret = mutableListOf<Int>()
        var p = head
        while (p != null) {
            ret.add(p.value)
            p = p.next
        }
        return ret
    }

    // recursive solution
    // T:O(n) S:O(logn)
    private var node: ListNode? = null

    fun sortedListToBSTRecur(head: ListNode?): TreeNode? {
        var length = 0
        var p = head
        while (p != null) {
            p = p.next
            length++
        }
        node = head
        return helper(0, length)
    }

    private fun helper(start: Int, end: Int): TreeNode? {
        if (start == end) {
            return null
        }
        val mid = start + (end - start).shr(1)
        val left = helper(start, mid)
        val root = TreeNode(node?.value ?: 0)
        root.left = left
        node = node?.next
        root.right = helper(mid + 1, end)
        return root
    }

}

fun main(args: Array<String>) {
    // LC OJ passed
    // see [ConvertSortedListToBinarySearchTreeTest] for unit test
}