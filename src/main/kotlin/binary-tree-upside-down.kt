/**
 * Given a binary tree where all the right nodes are either leaf nodes with a sibling
 * (a left node that shares the same parent node) or empty, flip it upside down and
 * turn it into a tree where the original right nodes turned into left leaf nodes.
 * Return the new root.
 *
 * For example:
 * Given a binary tree {1,2,3,4,5},
 *
 *     1
 *    / \
 *   2   3
 *  / \
 * 4   5
 *
 * return the root of the binary tree [4,5,2,*,*,3,1].
 *
 *    4
 *   / \
 *  5   2
 *     / \
 *    3   1
 */

import utils.TreeNode

class Solution_binary_tree_upside_down {
    // T:O(n) S:O(1)
    fun upSideDownIter(root: TreeNode): TreeNode {
        var cur: TreeNode? = root
        var parent: TreeNode? = null
        var parent_right: TreeNode? = null
        while (cur != null) {
            val left = cur.left
            cur.left = parent_right
            parent_right = cur.right
            cur.right = parent
            parent = cur
            cur = left
        }
        return parent!!
    }

    // T:O(n) S:O(n)
    fun upSideDownRecur(root: TreeNode): TreeNode {
        return helper(root, null)!!
    }

    fun helper(cur: TreeNode?, parent: TreeNode?): TreeNode? {
        if (cur == null)
            return parent
        val root = helper(cur.left, cur)
        if (parent != null)
            cur.left = parent.right
        else
            cur.left = null
        cur.right = parent
        return root
    }
}

fun main(args: Array<String>) {
    val s = Solution_binary_tree_upside_down()
    val root = TreeNode(1)
    root.left = TreeNode(2)
    root.right = TreeNode(3)
    root.left?.left = TreeNode(4)
    root.left?.right = TreeNode(5)
    println(Solution_binary_tree_preorder_traversal().preOrderWithStack(s.upSideDownIter(root)))

    val root2 = TreeNode(1)
    root2.left = TreeNode(2)
    root2.right = TreeNode(3)
    root2.left?.left = TreeNode(4)
    root2.left?.right = TreeNode(5)
    println(Solution_binary_tree_preorder_traversal().preOrderWithStack(s.upSideDownRecur(root2)))
}