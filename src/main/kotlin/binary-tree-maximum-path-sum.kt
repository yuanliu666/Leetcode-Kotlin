/**
 * Given a binary tree, find the maximum path sum.
 *
 * The path may start and end at any node in the tree.
 *
 * For example:
 * Given the below binary tree,
 *
 *        1
 *       / \
 *      2   3
 * Return 6.
 */

import utils.TreeNode

class Solution_binary_tree_maximum_path_sum {
    var ret = 0

    // T:O(n) S:O(h)
    fun getMaxPathSum(root: TreeNode): Int {
        ret = 0
        getMaxPathSumHelper(root)
        return ret
    }

    // return max path sum single branch from root, or 0 if the sum is less than 0
    fun getMaxPathSumHelper(root: TreeNode?): Int {
        var sum = 0
        if (root != null) {
            val left = getMaxPathSumHelper(root.left)
            val right = getMaxPathSumHelper(root.right)
            sum = maxOf(left + root.value, right + root.value, 0)
            ret = maxOf(left + right + root.value, ret)
        }
        return sum
    }
}

fun main(args: Array<String>) {
    val s = Solution_binary_tree_maximum_path_sum()
    val root = TreeNode(1)
    root.left = TreeNode(2)
    root.right = TreeNode(3)
    println(s.getMaxPathSum(root))
}