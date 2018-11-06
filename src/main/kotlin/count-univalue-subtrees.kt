/**
 * Given a binary tree, count the number of uni-value subtrees.
 *
 * A Uni-value subtree means all nodes of the subtree have the same value.
 *
 * For example:
 * Given binary tree,
 *
 *     5
 *    / \
 *   1   5
 *  / \   \
 * 5   5   5
 *
 *
 * return 4.
 */

import utils.TreeNode

class Solution_count_univalue_subtrees {

    // T:O(n) S:O(h)
    fun countUnivalSubtrees(node: TreeNode): Int {
        return helper(node).second
    }

    // return (isWholeTreeUni-ValueTree, Uni-ValueTreeCount) pair
    private fun helper(node: TreeNode?): Pair<Boolean, Int> {
        return if (node == null) {
            Pair(true, 0)
        } else {
            val pl = helper(node.left)
            val pr = helper(node.right)
            var cnt = pl.second + pr.second
            var flag = false
            // check if the tree includes node is another count
            if (isRootUniValueWithChild(node, node.left, pl.first)
                    && isRootUniValueWithChild(node, node.right, pr.first)) {
                cnt++
                flag = true
            }
            return Pair(flag, cnt)
        }
    }

    private fun isRootUniValueWithChild(root: TreeNode, child: TreeNode?, isChildUniValueTree: Boolean): Boolean {
        return child == null || (isChildUniValueTree && root.value == child.value)
    }
}

fun main(args: Array<String>) {

}