/**
 * Given a non-empty binary search tree and a target value,
 * find the value in the BST that is closest to the target.
 *
 * Note: Given target value is a floating point.
 * You are guaranteed to have only one unique value in the BST that is closest to the target.
 */

import utils.TreeNode

class Solution_closest_binary_search_tree_value {
    var ret: Int = 0

    // Recursive
    // T:O(h) S:O(h)
    fun getClosestValueRecur(root: TreeNode, t: Float): Int {
        ret = root.value
        helper(root, t)
        return ret
    }

    fun helper(node: TreeNode, t: Float) {
        when {
            node.value > t -> {
                if (Math.abs(ret - t) > node.value - t) {
                    ret = node.value
                }
                node.left?.let {
                    helper(it, t)
                }
            }
            node.value < t -> {
                if (Math.abs(ret - t) > t - node.value) {
                    ret = node.value
                }
                node.right?.let {
                    helper(it, t)
                }
            }
            else -> {
                ret = node.value
            }
        }
    }

    // T:O(h) S:O(1)
    fun getClosestValueIter(root: TreeNode, t: Float): Int {
        var ans = root.value
        var cur: TreeNode? = root
        while (cur != null) {
            if (Math.abs(ans - t) > Math.abs(cur.value - t)) {
                ans = cur.value
            }
            when {
                cur.value.toFloat() == t -> return cur.value
                cur.value < t -> cur = cur.right
                cur.value > t -> cur = cur.left
            }
        }
        return ans
    }
}

fun main(args: Array<String>) {
    val s = Solution_closest_binary_search_tree_value()
    val r = TreeNode(2)
    r.left = TreeNode(1)
    r.right = TreeNode(3)
    println(s.getClosestValueRecur(r, 1.1f))
    println(s.getClosestValueIter(r, 1.1f))
}