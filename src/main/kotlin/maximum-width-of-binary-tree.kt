/**
 * Given the root of a binary tree, return the maximum width of the given tree.
 * The maximum width of a tree is the maximum width among all levels.
 * The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes),
 * where the null nodes between the end-nodes that would be present in a complete binary tree extending down to that level
 * are also counted into the length calculation.
 * It is guaranteed that the answer will in the range of a 32-bit signed integer.
 */
class Solution_maximum_width_of_binary_tree {

    // Level traversal with index solution.
    // At nth level, there will be 2^(n-1) nodes.
    // Imagine root is 0, going left appends a 0 and going right appends a 1,
    // we create an index system including those null positions.
    // Then do level traversal while keep nodes left to right order, we only need to calculate width from first and last.
    // T:O(n) as we iterate tree once; S:(n)
    // Keep in mind of index overflow. We could use binary string for that if necessary.
    fun widthOfBinaryTree(root: TreeNode?): Int {
        if (root == null) return 0

        val lv = mutableListOf(Pair(0, root))
        var ret = 1
        while (lv.isNotEmpty()) {
            val nextLv = mutableListOf<Pair<Int, TreeNode>>()
            val left = lv[0]
            val right = lv[lv.size - 1]
            val w = right.first - left.first + 1
            ret = kotlin.math.max(ret, w)
            for (p in lv) {
                val index = p.first
                val node = p.second
                node.left?.let {
                    nextLv.add(Pair(index shl 1, it))
                }
                node.right?.let {
                    nextLv.add(Pair((index shl 1) + 1, it))
                }
            }
            lv.clear()
            lv.addAll(nextLv)
        }
        return ret
    }
}