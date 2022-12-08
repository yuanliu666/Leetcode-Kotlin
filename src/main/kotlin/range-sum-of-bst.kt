/**
 * Given the root node of a binary search tree and two integers low and high,
 * return the sum of values of all nodes with a value in the inclusive range [low, high].
 */
class Solution_range_sum_of_bst {

    // T:O(n) S:O(h)
    fun rangeSumBST(root: TreeNode?, low: Int, high: Int): Int {
        if (root == null) return 0
        var ret = 0
        if (root.`val` in low..high) ret += root.`val`
        if (low < root.`val`) ret += rangeSumBST(root.left, low, high)
        if (high > root.`val`) ret += rangeSumBST(root.right, low, high)
        return ret
    }
}