/**
 * Implement an iterator over a binary search tree (BST).
 * Your iterator will be initialized with the root node of a BST.
 *
 * Calling next() will return the next smallest number in the BST.
 *
 * Note: next() and hasNext() should run in average O(1) time
 * and uses O(h) memory, where h is the height of the tree.
 */

import utils.TreeNode
import java.util.*

// T:O(1) S:O(h)
class Solution_binary_search_tree_iterator(root: TreeNode) {
    val stk by lazy { Stack<TreeNode>() }
    var cur: TreeNode? = root

    fun next(): Int {
        while (cur != null) {
            stk.push(cur)
            cur = cur?.left
        }
        cur = stk.pop()
        val ret = cur?.value
        cur = cur?.right
        return ret!!
    }

    fun hasNext(): Boolean {
        return stk.isNotEmpty() || cur != null
    }
}

fun main(args: Array<String>) {
    val t4 = TreeNode(4)
    val s = Solution_binary_search_tree_iterator(t4)
    val t3 = TreeNode(3)
    val t2 = TreeNode(2)
    val t1 = TreeNode(1)
    t4.left = t2
    t2.left = t1
    t2.right = t3

    while (s.hasNext())
        println(s.next())
}
