/**
 * Given a binary tree, return the zigzag level order traversal of its nodes' values.
 * (ie, from left to right, then right to left for the next level and alternate between).
 *
 * For example:
 * Given binary tree {3,9,20,*,*,15,7},
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its zigzag level order traversal as:
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 */
import utils.TreeNode

class Solution_binary_tree_zigzag_level_order_traversal {
    // T:O(n) S:O(n)
    fun getZigzag(root: TreeNode): List<List<Int>> {
        var curLevel = mutableListOf<TreeNode>()
        val ret = mutableListOf<List<Int>>()
        var isLeftToRight = true
        curLevel.add(root)
        while (curLevel.isNotEmpty()) {
            val values = mutableListOf<Int>()
            val nextLevel = mutableListOf<TreeNode>()
            curLevel.forEach {
                values.add(it.value)
                it.left?.let { nextLevel.add(it) }
                it.right?.let { nextLevel.add(it) }
            }
            if (!isLeftToRight) {
                ret.add(values.reversed())
            } else {
                ret.add(values)
            }
            isLeftToRight = !isLeftToRight
            curLevel = nextLevel
        }
        return ret
    }
}

fun main(args: Array<String>) {
    val s = Solution_binary_tree_zigzag_level_order_traversal()
    val r = TreeNode(3)
    r.left = TreeNode(9)
    r.right = TreeNode(20)
    r.right?.left = TreeNode(15)
    r.right?.right = TreeNode(7)
    println(s.getZigzag(r))
}