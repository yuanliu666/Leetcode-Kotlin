/**
 * Given a binary tree, return the tilt of the whole tree.
 *
 * The tilt of a tree node is defined as the absolute difference
 * between the sum of all left subtree node values and
 * the sum of all right subtree node values. Null node has tilt 0.
 *
 * The tilt of the whole tree is defined as the sum of all nodes' tilt.
 *
 * Example:
 * Input:
 *          1
 *       /   \
 *       2     3
 * Output: 1
 * Explanation:
 * Tilt of node 2 : 0
 * Tilt of node 3 : 0
 * Tilt of node 1 : |2-3| = 1
 * Tilt of binary tree : 0 + 0 + 1 = 1
 * Note:
 *
 * The sum of node values in any subtree won't exceed the range of 32-bit integer.
 */

import utils.TreeNode

class Solution_binary_tree_tilt {
    // T:O(n) S:O(n)
    fun getTiltSum(root: TreeNode): Int {
        return getSumTiltPair(root, 0).second
    }

    fun getSumTiltPair(root: TreeNode?, tilt: Int): Pair<Int, Int> {
        if (root == null)
            return Pair(0, tilt)
        else {
            val leftPair = getSumTiltPair(root.left, tilt)
            val rightPair = getSumTiltPair(root.right, tilt)
            return Pair(leftPair.first + rightPair.first + root.value, tilt + Math.abs(leftPair.first - rightPair.first))
        }
    }
}

fun main(args: Array<String>) {
    val s = Solution_binary_tree_tilt()
    val r1 = TreeNode(1)
    r1.left = TreeNode(2)
    r1.right = TreeNode(3)
    println(s.getTiltSum(r1))
}
