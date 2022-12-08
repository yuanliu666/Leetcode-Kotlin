/**
 * Consider all the leaves of a binary tree, from left to right order,
 * the values of those leaves form a leaf value sequence.
 * Two binary trees are considered leaf-similar if their leaf value sequence is the same.
 * Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.
 */
class Solution_leaf_similar_trees {

    // T:O(m+n) S:O(m+n)
    fun leafSimilar(root1: TreeNode?, root2: TreeNode?): Boolean {
        return getLeafs(root1) == getLeafs(root2)
    }

    private fun getLeafs(root: TreeNode?): List<Int> {
        val ret = mutableListOf<Int>()
        if (root != null) {
            if (root.left == null && root.right == null) {
                ret.add(root.`val`)
            } else {
                val left = getLeafs(root.left)
                val right = getLeafs(root.right)
                ret.addAll(left)
                ret.addAll(right)
            }
        }
        return ret
    }
}