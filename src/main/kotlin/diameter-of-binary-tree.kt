/**
 * Given the root of a binary tree, return the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 * The length of a path between two nodes is represented by the number of edges between them.
 */
class Solution_diameter_of_binary_tree {

    private var max = 1

    // T:O(n) S:O(h)
    fun diameterOfBinaryTree(root: TreeNode?): Int {
        max = 1
        helper(root)
        return max - 1
    }

    // Get max depth
    private fun helper(root: TreeNode?): Int {
        if (root == null) return 0
        val left = helper(root.left)
        val right = helper(root.right)
        val cur = left + right + 1
        if (cur > max) max = cur
        return 1 + kotlin.math.max(left, right)
    }
}