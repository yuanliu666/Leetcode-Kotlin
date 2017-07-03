/**
 * Given a binary tree, return the preorder traversal of its nodes' values.
 *
 * For example:
 * Given binary tree {1,*,2,3},
 *    1
 *     \
 *      2
 *     /
 *    3
 * return [1,2,3].
 *
 * Note: Recursive solution is trivial, could you do it iteratively?
 */

import utils.TreeNode
import java.util.*

class Solution_binary_tree_preorder_traversal {
    // T:O(n) S:O(h)
    fun preOrderWithStack(root: TreeNode): List<Int> {
        val stk = Stack<TreeNode>()
        val ret = mutableListOf<Int>()
        stk.push(root)
        while (stk.isNotEmpty()) {
            val cur = stk.pop()
            ret.add(cur.value)
            cur.right?.let { stk.push(cur.right) }
            cur.left?.let { stk.push(cur.left) }
        }
        return ret
    }

    // T:O(n) S:O(h)
    // This solution looks more verbose than first one,
    // but it shares one similar solution across 3 types of binary tree traversal,
    // which makes it very easy to remember and write
    fun preOrderWithStack2(root: TreeNode): List<Int> {
        val ret = mutableListOf<Int>()
        val stk = Stack<Pair<TreeNode?, Boolean>>()
        stk.push(Pair(root, false))
        while (stk.isNotEmpty()) {
            val p = stk.pop()
            val cur = p.first
            val isVisited = p.second
            if (cur == null)
                continue
            else {
                if (isVisited)
                    ret.add(cur.value)
                else {
                    // only this part differs for different kinds of traversals
                    stk.push(Pair(cur.right, false))
                    stk.push(Pair(cur.left, false))
                    stk.push(Pair(cur, true))
                }
            }
        }
        return ret
    }

    // T:O(n) S:O(1)
    fun preOrderMorris(root: TreeNode): List<Int> {
        val ret = mutableListOf<Int>()
        var cur: TreeNode? = root
        while (cur != null) {
            if (cur.left == null) {
                ret.add(cur.value)
                cur = cur.right
            } else {
                var node = cur.left
                while (node?.right != null && node.right != cur)
                    node = node.right
                if (node?.right == null) {
                    ret.add(cur.value)
                    node?.right = cur
                    cur = cur.left
                } else {
                    node.right = null
                    cur = cur.right
                }
            }
        }
        return ret
    }
}

fun main(args: Array<String>) {
    val s = Solution_binary_tree_preorder_traversal()
    val root = TreeNode(1)
    root.right = TreeNode(2)
    root.right?.left = TreeNode(3)
    println(s.preOrderWithStack(root))
    println(s.preOrderWithStack2(root))
    println(s.preOrderMorris(root))
}
