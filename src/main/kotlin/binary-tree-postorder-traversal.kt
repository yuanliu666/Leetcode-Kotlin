/**
 * Given a binary tree, return the postorder traversal of its nodes' values.
 *
 * For example:
 * Given binary tree {1,*,2,3},
 *   1
 *    \
 *     2
 *    /
 *   3
 * return [3,2,1].
 *
 * Note: Recursive solution is trivial, could you do it iteratively?
 */

import utils.TreeNode
import java.util.*

class Solution_binary_tree_postorder_traversal {
    // T:O(n) S:O(h)
    fun postOrderWithDoubleStacks(root: TreeNode): List<Int> {
        val stk1 = Stack<TreeNode>()
        val stk2 = Stack<TreeNode>()
        stk1.push(root)
        while (stk1.isNotEmpty()) {
            val cur = stk1.pop()
            stk2.push(cur)
            cur.left?.let { stk1.push(it) }
            cur.right?.let { stk1.push(it) }
        }
        return stk2.map { it.value }.reversed()
    }

    // T:O(n) S:O(h)
    // This solution looks more verbose than first one,
    // but it shares one similar solution across 3 types of binary tree traversal,
    // which makes it very easy to remember and write
    fun postOrderWithStack(root: TreeNode): List<Int> {
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
                    stk.push(Pair(cur, true))
                    stk.push(Pair(cur.right, false))
                    stk.push(Pair(cur.left, false))
                }
            }
        }
        return ret
    }

    // T:O(n) S:O(1)
    fun postOrderMorris(root: TreeNode): List<Int> {
        val dummy = TreeNode()
        dummy.left = root
        val ret = mutableListOf<Int>()
        var cur: TreeNode? = dummy
        while (cur != null) {
            if (cur.left == null) {
                cur = cur.right
            } else {
                var node = cur.left
                while (node?.right != null && node.right != cur)
                    node = node.right

                if (node?.right == null) {
                    node?.right = cur
                    cur = cur.left
                } else {
                    ret.addAll(traceBack(cur.left!!, node))
                    node.right = null
                    cur = cur.right
                }
            }
        }
        return ret
    }

    fun traceBack(from: TreeNode, to: TreeNode): List<Int> {
        val ret = mutableListOf<Int>()
        var cur: TreeNode = from
        while (cur !== to) {
            ret.add(cur.value)
            cur = cur.right!!
        }
        ret.add(to.value)
        return ret.reversed()
    }
}

fun main(args: Array<String>) {
    val s = Solution_binary_tree_postorder_traversal()
    val root = TreeNode(1)
    root.right = TreeNode(2)
    root.right?.left = TreeNode(3)
    println(s.postOrderWithDoubleStacks(root))
    println(s.postOrderWithStack(root))
    println(s.postOrderMorris(root))
}
