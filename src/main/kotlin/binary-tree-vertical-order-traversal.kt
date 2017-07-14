/**
 * Given a binary tree, return the vertical order traversal of its nodes' values.
 * (ie, from top to bottom, column by column).
 *
 * If two nodes are in the same row and column, the order should be from left to right.
 *
 * Examples:
 * Given binary tree [3,9,20,null,null,15,7],
 *
 *    3
 *   / \
 *  9  20
 *    /  \
 *   15   7
 *
 *
 * return its vertical order traversal as:
 *
 * [
 * [9],
 * [3,15],
 * [20],
 * [7]
 * ]
 *
 *
 * Given binary tree [3,9,20,4,5,2,7],
 *
 *     _3_
 *    /   \
 *   9    20
 * /  \   / \
 * 4   5 2   7
 *
 *
 * return its vertical order traversal as:
 *
 * [
 * [4],
 * [9],
 * [3,5,2],
 * [20],
 * [7]
 * ]
 */
import utils.TreeNode

class Solution_binary_tree_vertical_order_traversal {
    // T:O(n) S:O(n)
    fun getVerticalOrderTraversal(root: TreeNode): List<List<Int>> {
        val q = mutableListOf<Pair<TreeNode, Int>>()
        q.add(Pair(root, 0))
        val map = mutableMapOf<Int, MutableList<Int>>()
        while (q.isNotEmpty()) {
            val next = mutableListOf<Pair<TreeNode, Int>>()
            q.forEach {
                val pos = it.second
                map.putIfAbsent(pos, mutableListOf())
                map[pos]?.add(it.first.value)
                it.first.left?.let { next.add(Pair(it, pos - 1)) }
                it.first.right?.let { next.add(Pair(it, pos + 1)) }
            }
            q.clear()
            q.addAll(next)
        }
        val ret = mutableListOf<List<Int>>()
        (map.keys.min()!!..map.keys.max()!!).forEach { ret.add(map[it]!!) }
        return ret
    }


}

fun main(args: Array<String>) {
    val s = Solution_binary_tree_vertical_order_traversal()

    val r1 = TreeNode(3)
    r1.left = TreeNode(9)
    r1.right = TreeNode(20)
    r1.right?.left = TreeNode(15)
    r1.right?.right = TreeNode(7)

    val r2 = TreeNode(3)
    r2.left = TreeNode(9)
    r2.left?.left = TreeNode(4)
    r2.left?.right = TreeNode(5)
    r2.right = TreeNode(20)
    r2.right?.left = TreeNode(2)
    r2.right?.right = TreeNode(7)

    println(s.getVerticalOrderTraversal(r1))
    println(s.getVerticalOrderTraversal(r2))
}