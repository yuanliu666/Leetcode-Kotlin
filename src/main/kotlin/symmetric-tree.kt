/**
 * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 */
class Solution_symmetric_tree {

    // T:O(n) S:O(h)
    fun isSymmetric(root: TreeNode?): Boolean {
        if (root == null) return true
        return helper(root.left, root.right)
    }

    private fun helper(left: TreeNode?, right: TreeNode?): Boolean {
        if (left == null && right == null) return true
        if (left == null || right == null) return false
        if (left.`val` != right.`val`) return false
        return helper(left.left, right.right) && helper(left.right, right.left)
    }
}