/**
 * Given a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 *
 * For example:
 * Given the following binary tree,
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 * You should return [1, 3, 4].
 */

import utils.TreeNode

class Solution_binary_tree_right_side_view {
    // T:O(n) S:O(h)
    fun getRightSideViewDFS(root: TreeNode): List<Int> {
        val ret = mutableListOf<Int>()
        ret.add(root.value)
        dfsHelper(root.right, 1, ret)
        dfsHelper(root.left, 1, ret)
        return ret
    }

    fun dfsHelper(cur: TreeNode?, depth: Int, ret: MutableList<Int>) {
        if (cur == null)
            return
        else {
            if (depth == ret.size)
                ret.add(cur.value)
            dfsHelper(cur.right, depth + 1, ret)
            dfsHelper(cur.left, depth + 1, ret)
        }
    }

    // T:O(n) S:O(h)
    fun getRightSideViewBFS(root: TreeNode): List<Int> {
        val ret = mutableListOf<Int>()
        var curLevel = mutableListOf(root)
        while (curLevel.isNotEmpty()) {
            ret.add(curLevel.last().value)
            val nextLevel = mutableListOf<TreeNode>()
            curLevel.forEach {
                it.left?.let { nextLevel.add(it) }
                it.right?.let { nextLevel.add(it) }
            }
            curLevel = nextLevel
        }
        return ret
    }
}

fun main(args: Array<String>) {
    val s = Solution_binary_tree_right_side_view()
    val root = TreeNode(1)
    root.left = TreeNode(2)
    root.right = TreeNode(3)
    root.right?.right = TreeNode(4)
    root.left?.right = TreeNode(5)
    println(s.getRightSideViewDFS(root))
    println(s.getRightSideViewBFS(root))
}
