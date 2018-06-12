import utils.BinaryTreePrinter
import utils.TreeNode

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * For example, given
 *
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * Return the following binary tree:
 *
 *   3
 *  / \
 * 9  20
 *   /  \
 *  15   7
 */

class Solution_construct_binary_tree_from_preorder_and_inorder_traversal {
    // T:O(n) S:O(n)
    // very similar to last question
    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        val lookup = mutableMapOf<Int, Int>()
        for ((idx, n) in inorder.withIndex()) {
            lookup[n] = idx
        }
        return helper(lookup, preorder, inorder, preorder.size, 0, inorder.size)
    }


    // still start index inclusive and end index exclusive
    private fun helper(lookup: Map<Int, Int>, preorder: IntArray, inorder: IntArray, preEnd: Int, inStart: Int, inEnd: Int): TreeNode? {
        if (inStart == inEnd) {
            return null
        }
        val preStart = preEnd + inStart - inEnd
        val root = TreeNode(preorder[preStart])
        val rootIdx = lookup[preorder[preStart]] ?: 0
        // left sub tree
        val leftTreeSize = rootIdx - inStart
        val leftPreStart = preStart + 1
        val leftPreEnd = leftTreeSize + leftPreStart
        root.left = helper(lookup, preorder, inorder, leftPreEnd, inStart, rootIdx)
        // right sub tree
        root.right = helper(lookup, preorder, inorder, preEnd, rootIdx + 1, inEnd)
        return root
    }
}

fun main(args: Array<String>) {
    // LC OJ Passed
    val s = Solution_construct_binary_tree_from_preorder_and_inorder_traversal()
    BinaryTreePrinter.printNode(s.buildTree(intArrayOf(3, 9, 20, 15, 7), intArrayOf(9, 3, 15, 20, 7)))
}