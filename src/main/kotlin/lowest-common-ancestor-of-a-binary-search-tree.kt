/**
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T
 * that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 *
 * The number of nodes in the tree is in the range [2, 105].
 * -10^9 <= Node.val <= 10^9
 * All Node.val are unique.
 * p != q
 * p and q will exist in the BST.
 */
class Solution_lowest_common_ancestor_of_a_binary_search_tree {

    // T:O(h) S:O(1)
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (root == null || p == null || q == null) return null
        // make sure p < q
        if (q.`val` < p.`val`) {
            return lowestCommonAncestor(root, q, p)
        }
        var n = root
        while (n != null) {
            if (n.`val` in p.`val`..q.`val`) {
                return n
            }
            n = if (n.`val` > q.`val`) {
                n.left!!
            } else {
                n.right!!
            }
        }
        return n
    }

    private var ret: TreeNode? = null

    // Solution that ignores the condition of BST
    fun lowestCommonAncestor2(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        ret = null
        postOrder(root, p!!.`val`, q!!.`val`)
        return ret
    }

    // Return if it is ancestor of p and q
    private fun postOrder(node: TreeNode?, pVal: Int, qVal: Int): BooleanArray {
        if (node == null || ret != null) return booleanArrayOf(false, false)
        val left = postOrder(node.left, pVal, qVal)
        val right = postOrder(node.right, pVal, qVal)
        val isAncestorP = left[0] || right[0] || node.`val` == pVal
        val isAncestorQ = left[1] || right[1] || node.`val` == qVal
        if (ret == null && isAncestorP && isAncestorQ) {
            ret = node
        }
        return booleanArrayOf(isAncestorP, isAncestorQ)
    }
}