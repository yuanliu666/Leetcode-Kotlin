/**
 * Given a binary tree, determine if it is height-balanced.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree
 * in which the depth of the two subtrees of every node never differ by more than 1.
 */

import utils.TreeNode

class Solution_balanced_binary_tree {
    // T:O(n) S:O(h)
    fun isBalancedTree(root: TreeNode): Boolean {
        return getHeight(root) >= 0
    }

    fun getHeight(cur: TreeNode?): Int {
        if (cur == null)
            return 0
        val lh = getHeight(cur.left)
        val rh = getHeight(cur.right)
        if (lh < 0 || rh < 0 || Math.abs(lh - rh) > 1)
            return -1
        return maxOf(lh, rh) + 1
    }
}

fun main(args: Array<String>) {

}