/**
 * Given the root of a binary tree, then value v and depth d,
 * you need to add a row of nodes with value v at the given depth d.
 * The root node is at depth 1.
 *
 * The adding rule is: given a positive integer depth d, for each NOT null tree nodes N in depth d-1,
 * create two tree nodes with value v as N's left subtree root and right subtree root.
 * And N's original left subtree should be the left subtree of the new left subtree root,
 * its original right subtree should be the right subtree of the new right subtree root.
 * If depth d is 1 that means there is no depth d-1 at all, then create a tree node with value v as the new root of
 * the whole original tree, and the original tree is the new root's left subtree.
 *
 * Example 1:
 * Input:
 * A binary tree as following:
 *      4
 *    /   \
 *   2     6
 *  / \   /
 * 3   1 5

 * v = 1
 *
 * d = 2
 *
 * Output:
 *       4
 *      / \
 *     1   1
 *    /     \
 *   2       6
 *  / \     /
 * 3   1   5
 *
 * Example 2:
 * Input:
 * A binary tree as following:
 *     4
 *    /
 *   2
 *  / \
 * 3   1
 *
 * v = 1
 *
 * d = 3
 *
 * Output:
 *       4
 *      /
 *     2
 *    / \
 *   1   1
 *  /     \
 * 3       1
 * Note:
 * 1. The given d is in range [1, maximum depth of the given tree + 1].
 * 2. The given binary tree has at least one tree node.
 */

import utils.BinaryTreePrinter
import utils.TreeNode

class Solution_add_one_row_to_tree {

    // I think there is problem with signature of this function,
    // root actually cannot be null and also we do not return null

    // BFS
    // Time:  O(n)
    // Space: O(h)
    fun addOneRow(root: TreeNode?, v: Int, d: Int): TreeNode? {
        if (root == null) {
            return root
        }
        if (d == 1) {
            val node = TreeNode(v)
            node.left = root
            return node
        }
        var list = mutableListOf(root)
        var curDepth = 1
        while (list.isNotEmpty()) {
            val nextList = mutableListOf<TreeNode>()
            for (n in list) {
                if (curDepth == d - 1) {
                    val left = TreeNode(v)
                    val right = TreeNode(v)
                    left.left = n.left
                    right.right = n.right
                    n.left = left
                    n.right = right
                    // no need to go deeper
                } else {
                    n.left?.let {
                        nextList.add(it)
                    }
                    n.right?.let {
                        nextList.add(it)
                    }
                }
            }
            curDepth++
            list = nextList
        }
        return root
    }

    // recursive
    fun addOneRow2(root: TreeNode?, v: Int, d: Int): TreeNode? {
        if (d == 0 || d == 1) {
            val node = TreeNode(v)
            if (d == 1) {
                node.left = root
            } else {
                node.right = root
            }
            return node
        }
        if (root != null && d >= 2) {
            root.left = addOneRow2(root.left, v, if (d > 2) {
                d - 1
            } else {
                1
            })
            root.right = addOneRow2(root.right, v, if (d > 2) {
                d - 1
            } else {
                0
            })
        }
        return root
    }
}

fun main(args: Array<String>) {
    // LC OJ passed
    val root1 = TreeNode(4)
    val left1 = TreeNode(2)
    val right1 = TreeNode(6)
    left1.left = TreeNode(3)
    left1.right = TreeNode(1)
    right1.left = TreeNode(5)
    root1.left = left1
    root1.right = right1
    BinaryTreePrinter.printNode(root1)
    BinaryTreePrinter.printNode(Solution_add_one_row_to_tree().addOneRow(root1, 1, 2))
}