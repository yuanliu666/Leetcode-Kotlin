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
            val values = mutableListOf<Int>()
            for (n in cur) {
                // note: change n.value to n.`val` if you're using this in Leetcode OJ
                values.add(n.value)
                n.left?.let {
                    next.add(it)
                }
                n.right?.let {
                    next.add(it)
                }
            }
            ret.add(getAverage(values))
            cur = next
        }
        return ret.toDoubleArray()
    }

    // I am surprised that in LC discussion no one considers that the sum could overflow?
    // to avoid this we use (n0 + n1 +... )/size = n0/size + n1/size + ...
    // but then we face the problem of precision loss
    // so we only do integer divide and use a parameter to accumulate remainder and also prevent it from overflow
    // at last we do double divide and add them up
    private fun getAverage(nums: List<Int>): Double {
        var ret = 0.0
        var remainder = 0
        for (n in nums) {
            ret += n / nums.size
            remainder += n % nums.size
            if (remainder >= nums.size) {
                ret += remainder / nums.size
                remainder %= nums.size
            }
        }
        return ret + (remainder.toDouble() / nums.size)
    }

    // solution that does not consider sum overflow
    fun averageOfLevels2(root: TreeNode?): DoubleArray {
        val ret = mutableListOf<Double>()
        if (root == null) {
            return ret.toDoubleArray()
        }
        var cur: List<TreeNode> = listOf(root)
        while (cur.isNotEmpty()) {
            val next = mutableListOf<TreeNode>()
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