/**
 * Given a binary tree, return the level order traversal of its nodes' values.
 * (ie, from left to right, level by level).
 *
 * For example:
 * Given binary tree {3,9,20,*,*,15,7},
 *    3
 *   / \
 *  9  20
 *    /  \
 *   15   7
 * return its level order traversal as:
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */

import utils.TreeNode

class Solution_binary_tree_level_order_traversal {
    // T:O(n) S:O(n)
    fun getLevelOrder(root: TreeNode): List<List<Int>> {
        val ret = mutableListOf<List<Int>>()
        val level = mutableListOf(root)
        val nextLevel = mutableListOf<TreeNode>()
        while (level.isNotEmpty()) {
            ret.add(level.map { it.value })
            nextLevel.clear()
            level.forEach {
                if (it.left != null)
                    nextLevel.add(it.left!!)
                if (it.right != null)
                    nextLevel.add(it.right!!)
            }
            level.clear()
            level.addAll(nextLevel)
        }
        return ret
    }

    // T:O(n) S:O(h) as recursion stack can be size of tree height
    fun levelOrder(root: TreeNode?): List<List<Int>> {
        val ret = mutableListOf<MutableList<Int>>()
        recur(root, ret, 0)
        return ret
    }

    private fun recur(n: TreeNode?, ret: MutableList<MutableList<Int>>, idx: Int) {
        if (n == null) return
        if (idx == ret.size) {
            ret.add(mutableListOf())
        }
        ret[idx].add(n.value)
        recur(n.left, ret, idx + 1)
        recur(n.right, ret, idx + 1)
    }

    // T:O(n) S:O(n) as list holds level of nodes which can be (n+1)/2
    fun levelOrder2(root: TreeNode?): List<List<Int>> {
        val ret = mutableListOf<MutableList<Int>>()
        if (root == null) return ret
        val list = mutableListOf<TreeNode>()
        list.add(root)
        var idx = 0
        while (list.isNotEmpty()) {
            ret.add(mutableListOf())
            val next = mutableListOf<TreeNode>()
            for (n in list) {
                ret[idx].add(n.value)
                n.left?.let {
                    next.add(it)
                }
                n.right?.let {
                    next.add(it)
                }
            }
            idx++
            list.clear()
            list.addAll(next)
        }
        return ret
    }
}

fun main(args: Array<String>) {
    val s = Solution_binary_tree_level_order_traversal()
    val root = TreeNode(3)
    root.left = TreeNode(9)
    root.right = TreeNode(20)
    root.right!!.left = TreeNode(15)
    root.right!!.right = TreeNode(7)
    println(s.getLevelOrder(root))
}