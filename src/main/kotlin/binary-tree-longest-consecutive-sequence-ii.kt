/**
 * Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.
 *
 * Especially, this path can be either increasing or decreasing.
 * For example, [1,2,3,4] and [4,3,2,1] are both considered valid,
 * but the path [1,2,4,3] is not valid. On the other hand,
 * the path can be in the child-Parent-child order,
 * where not necessarily be parent-child order.
 *
 * Example 1:
 *
 * Input:
 *   1
 *  / \
 * 2   3
 * Output: 2
 * Explanation: The longest consecutive path is [1, 2] or [2, 1].
 *
 * Example 2:
 *
 * Input:
 *   2
 *  / \
 * 1   3
 * Output: 3
 * Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
 *
 * Note: All the values of tree nodes are in the range of [-1e7, 1e7].
 */

import utils.TreeNode

class Solution_binary_tree_longest_consecutive_sequence_ii {
    var ret = 0

    // T:O(n) S:O(n)
    fun getLongestConsecutivePathLength(root: TreeNode): Int {
        ret = 0
        helper(root)
        return ret
    }

    // return longest path in ascending and descending order as a pair (a, d)
    // ascending means values increment from root to leaf, descending means opposite
    fun helper(root: TreeNode?): Pair<Int, Int> {
        if (root == null)
            return Pair(0, 0)
        var ascending = 1
        var descending = 1
        val leftPair = helper(root.left)
        val rightPair = helper(root.right)
        if (root.left != null) {
            if (root.left!!.value == root.value + 1)
                ascending = maxOf(ascending, leftPair.first + 1)
            else if (root.left!!.value == root.value - 1)
                descending = maxOf(descending, leftPair.second + 1)
        }
        if (root.right != null) {
            if (root.right!!.value == root.value + 1)
                ascending = maxOf(ascending, rightPair.first + 1)
            else if (root.right!!.value == root.value - 1)
                descending = maxOf(descending, rightPair.second + 1)
        }
        // root has been calculated twice, should minus 1
        // actually there are several cases, but they nicely converge here
        ret = maxOf(ret, ascending + descending - 1)
        return Pair(ascending, descending)
    }
}

fun main(args: Array<String>) {
    val s = Solution_binary_tree_longest_consecutive_sequence_ii()
    val r1 = TreeNode(1)
    r1.left = TreeNode(2)
    r1.right = TreeNode(3)
    println(s.getLongestConsecutivePathLength(r1))

    val r2 = TreeNode(2)
    r2.left = TreeNode(1)
    r2.right = TreeNode(3)
    println(s.getLongestConsecutivePathLength(r2))
}
