/**
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values.
 *  (ie, from left to right, level by level from leaf to root).
 *
 * For example:
 * Given binary tree {3,9,20,*,*,15,7},
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its bottom-up level order traversal as:
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 */

import utils.TreeNode

class Solution_binary_tree_level_order_traversal_ii {
    // T:O(n) S:O(n)
    fun getBottomUpLevelOrder(root: TreeNode): List<List<Int>> {
        return Solution_binary_tree_level_order_traversal().getLevelOrder(root).reversed()
    }
}

fun main(args: Array<String>) {
    val s = Solution_binary_tree_level_order_traversal_ii()
    val root = TreeNode(3)
    root.left = TreeNode(9)
    root.right = TreeNode(20)
    root.right!!.left = TreeNode(15)
    root.right!!.right = TreeNode(7)
    println(s.getBottomUpLevelOrder(root))
}