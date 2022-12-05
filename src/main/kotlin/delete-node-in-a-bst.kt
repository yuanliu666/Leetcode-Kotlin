/**
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST.
 * Return the root node reference (possibly updated) of the BST.
 * Basically, the deletion can be divided into two stages:
 * Search for a node to remove.
 * If the node is found, delete the node.
 *
 * The number of nodes in the tree is in the range [0, 10^4].
 * -10^5 <= Node.val <= 10^5
 * Each node has a unique value.
 * root is a valid binary search tree.
 * -10^5 <= key <= 10^5
 */
class Solution_delete_node_in_a_bst {

    // T:O(h) as we only go left or right, S:O(h)
    fun deleteNode(root: TreeNode?, key: Int): TreeNode? {
        if (root == null) return null
        when {
            root.`val` > key -> root.left = deleteNode(root.left, key)
            root.`val` < key -> root.right = deleteNode(root.right, key)
            else -> {
                if (root.left == null) return root.right
                if (root.right == null) return root.left
                val minNode = findMin(root.right!!)
                // This is the neat part: instead of actually deleting and handle the connection of parent, children, etc.,
                // swapping value and then use recursion to delete the min node
                root.`val` = minNode.`val`
                root.right = deleteNode(root.right, root.`val`)
            }
        }
        return root
    }

    private fun findMin(node: TreeNode): TreeNode {
        var n = node
        while (n.left != null) {
            n = n.left!!
        }
        return n
    }
}