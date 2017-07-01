/**
 * Given a binary tree, find the length of the longest consecutive sequence path.
 *
 * The path refers to any sequence of nodes from some starting node
 * to any node in the tree along the parent-child connections.
 * The longest consecutive path need to be from parent to child (cannot be the reverse).
 *
 * For example,
 *
 * 1
 *  \
 *   3
 *  / \
 * 2   4
 *      \
 *       5
 * Longest consecutive sequence path is 3-4-5, so return 3.
 *
 *  2
 *   \
 *    3
 *   /
 *  2
 * /
 * 1
 * Longest consecutive sequence path is 2-3,not3-2-1, so return 2.
 */

import utils.TreeNode

class Solution_binary_tree_longest_consecutive_sequence {
    // T:O(n) S:O(h)
    fun getLongestConsecutiveSequenceLength(root: TreeNode): Int {
        var left = 0
        var right = 0
        var len = 1
        if (root.left != null) {
            left = getLongestConsecutiveSequenceLength(root.left!!)
            if (root.value + 1 == root.left?.value)
                len = maxOf(len, len + left)
        }
        if (root.right != null) {
            right = getLongestConsecutiveSequenceLength(root.right!!)
            if (root.value + 1 == root.right?.value)
                len = maxOf(len, len + right)
        }
        return maxOf(len, left, right)
    }
}

fun main(args: Array<String>) {
    val s = Solution_binary_tree_longest_consecutive_sequence()
    val r1 = TreeNode(1)
    r1.right = TreeNode(3)
    r1.right?.left = TreeNode(2)
    r1.right?.right = TreeNode(4)
    r1.right?.right?.right = TreeNode(5)
    println(s.getLongestConsecutiveSequenceLength(r1))

    val r2 = TreeNode(2)
    r2.right = TreeNode(3)
    r2.right?.left = TreeNode(2)
    r2.right?.left?.left = TreeNode(1)
    println(s.getLongestConsecutiveSequenceLength(r2))
}