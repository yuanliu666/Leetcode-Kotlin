/**
 * Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
 * Example 1:
 * Input:
 *      3
 *     / \
 *    9  20
 *  /  \
 * 15   7
 * Output: [3, 14.5, 11]
 * Explanation:
 * The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
 * Note:
 * The range of node's value is in the range of 32-bit signed integer.
 */

import utils.TreeNode

class Solution_average_of_levels_in_binary_tree {

    // T:O(n) S:O(h)
    fun averageOfLevels(root: TreeNode?): DoubleArray {
        val ret = mutableListOf<Double>()
        if (root == null) {
            return ret.toDoubleArray()
        }
        var cur: List<TreeNode> = listOf(root)
        while (cur.isNotEmpty()) {
            val next = mutableListOf<TreeNode>()
            // sum could overflow as int so we use double
            var values = 0.0
            for (n in cur) {
                // note: change n.value to n.`val` if you're using this in Leetcode OJ
                values += n.value
                n.left?.let {
                    next.add(it)
                }
                n.right?.let {
                    next.add(it)
                }
            }
            ret.add(values / cur.size)
            cur = next
        }
        return ret.toDoubleArray()
    }
}

fun main(args: Array<String>) {
    // LC OJ passed
}