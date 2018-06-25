/**
 * Given a Binary Search Tree (BST), convert it to a Greater Tree such that
 * every key of the original BST is changed to the original key plus sum of all keys greater than
 * the original key in BST.
 *
 * Example:
 *
 * Input: The root of a Binary Search Tree like this:
 *    5
 *  /   \
 * 2     13
 *
 * Output: The root of a Greater Tree like this:
 *     18
 *   /   \
 * 20     13
 */

import utils.TreeNode

class Solution_convert_bst_to_greater_tree {

    // T:O(n) S:O(h) for both solutions

    // solution with double inorder traversal
    private var sum = 0

    fun convertBST(root: TreeNode?): TreeNode? {
        val list = mutableListOf<Int>()
        inorderToGetSum(root, list)
        sum = list.sum()
        inorderAgain(root)
        return root
    }

    private fun inorderToGetSum(root: TreeNode?, list: MutableList<Int>) {
        if (root == null) {
            return
        } else {
            inorderToGetSum(root.left, list)
            list.add(root.value)
            inorderToGetSum(root.right, list)
        }
    }

    private fun inorderAgain(root: TreeNode?) {
        if (root == null) {
            return
        } else {
            inorderAgain(root.left)
            val v = root.value
            root.value = sum
            sum -= v
            inorderAgain(root.right)
        }
    }

    // recursive solution
    fun convertBSTRec(root: TreeNode?): TreeNode? {
        helper(root, 0)
        return root
    }

    // return current accumulated sum, reverse inorder order (right->root->left)
    private fun helper(root: TreeNode?, curSum: Int): Int {
        if (root == null) {
            return curSum
        }
        var cs = curSum
        root.right?.let {
            cs = helper(root.right, cs)
        }
        cs += root.value
        root.value = cs
        root.left?.let {
            cs = helper(root.left, cs)
        }
        return cs
    }
}

fun main(args: Array<String>) {
    // LC OJ Passed
    // see [ConvertBstToGreaterTreeTest] for unit test
}