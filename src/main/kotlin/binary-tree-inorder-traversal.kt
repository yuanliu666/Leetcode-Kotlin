import utils.TreeNode
import java.util.*

/**
 * Given a binary tree, return the inorder traversal of its nodes' values.
 *
 * For example:
 * Given binary tree {1,*,2,3},
 *   1
 *    \
 *     2
 *    /
 *   3
 * return [1,3,2].
 *
 * Note: Recursive solution is trivial, could you do it iteratively?
 */

class Solution_binary_tree_inorder_traversal {
    // T:O(n) S:O(h)
    fun inOrderWithStack(root: TreeNode): List<Int> {
        val stk = Stack<TreeNode>()
        val ret = mutableListOf<Int>()
        var cur: TreeNode? = root
        while (cur != null || stk.isNotEmpty()) {
            while (cur != null) {
                stk.push(cur)
                cur = cur.left
            }
            cur = stk.pop()
            ret.add(cur.value)
            cur = cur.right
        }
        return ret
    }

    // T:O(n) S:O(1)
    fun inOrderMorris(root: TreeNode): List<Int> {
        val ret = mutableListOf<Int>()
        var cur: TreeNode? = root
        while (cur != null) {
            if (cur.left == null) {
                ret.add(cur.value)
                cur = cur.right
            } else {
                var node: TreeNode = cur.left!!
                while (node.right != null && node.right != cur)
                    node = node.right!!
                if (node.right == null) {
                    node.right = cur
                    cur = cur.left
                } else {
                    ret.add(cur.value)
                    node.right = null
                    cur = cur.right
                }

            }
        }
        return ret
    }
}

fun main(args: Array<String>) {
    val t4 = TreeNode(4)
    val s = Solution_binary_tree_inorder_traversal()
    val t3 = TreeNode(3)
    val t2 = TreeNode(2)
    val t1 = TreeNode(1)
    t4.left = t2
    t2.left = t1
    t2.right = t3
    println(s.inOrderWithStack(t4))
    println(s.inOrderMorris(t4))
}
