/**
 * Given the root of a binary search tree, and an integer k,
 * return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
 *
 * The number of nodes in the tree is n.
 * 1 <= k <= n <= 10^4
 * 0 <= Node.val <= 10^4
 */
class Solution_kth_smallest_element_in_a_bst {

    private var cnt = 0

    // Use in order traversal
    // T:O(n) S:O(h)
    fun kthSmallest(root: TreeNode?, k: Int): Int {
        cnt = 0
        return inOrder(root, k)
    }

    private fun inOrder(root: TreeNode?, k: Int): Int {
        if (root == null) return -1

        val left = inOrder(root.left, k)
        if (left >= 0) return left

        cnt++
        if (cnt == k) return root.`val`

        val right = inOrder(root.right, k)
        if (right >= 0) return right
        return -1
    }

    // Follow up: If the BST is modified often (i.e., we can do insert and delete operations)
    // and you need to find the kth smallest frequently, how would you optimize?
    // If we want to prioritize finding kth smallest, we can map the tree with a sorted array.
    // So insert/remove will be O(n) but finding kth will be O(1).
}