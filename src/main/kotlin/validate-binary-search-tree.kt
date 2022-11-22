/**
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 * A valid BST is defined as follows:
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 */

class Solution_validate_binary_search_tree {

    private var prev: Int? = null
    private var isValid = true

    // Using in order, T:O(n) s:O(h)
    fun isValidBST(root: TreeNode?): Boolean {
        inOrder(root)
        return isValid
    }

    private fun inOrder(node: TreeNode?) {
        if (!isValid || node == null) return

        inOrder(node.left)

        if (prev != null && node.`val` <= prev!!) {
            isValid = false
            return
        }
        prev = node.`val`

        inOrder(node.right)
    }
}