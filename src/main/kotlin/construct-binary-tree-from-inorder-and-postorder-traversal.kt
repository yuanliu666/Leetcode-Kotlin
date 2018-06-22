import utils.TreeNode

/**
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * For example, given
 *
 * inorder = [9,3,15,20,7]
 * postorder = [9,15,7,20,3]
 * Return the following binary tree:
 *
 *    3
 *   / \
 *  9  20
 *    /  \
 *   15   7
 */

class Solution_construct_binary_tree_from_inorder_and_postorder_traversal {

    // T:O(n) S:O(n)

    // first get root from last element of post order list
    // then from inorder we can find left sub tree and right sub tree
    // after that split post order list to get its left and right tree accordingly with size from inorder
    fun buildTree(inorder: IntArray, postorder: IntArray): TreeNode? {
        // lookup table for inorder index
        val lookup = mutableMapOf<Int, Int>()
        for ((idx, n) in inorder.withIndex()) {
            lookup[n] = idx
        }
        return helper(lookup, postorder, inorder, postorder.size, 0, inorder.size)
    }

    // use start and end indexes instead of sublist to save space because subtree list are consecutive
    // also since tree size is the same for post/in order so 3 parameters for post and in order list is enough
    // start index is inclusive, end index is exclusive
    private fun helper(lookup: Map<Int, Int>,
                       postorder: IntArray,
                       inorder: IntArray,
                       postEnd: Int,
                       inStart: Int,
                       inEnd: Int): TreeNode? {
        if (inStart == inEnd) {
            return null
        }
        val node = TreeNode(postorder[postEnd - 1])
        // index of root
        val i = lookup[postorder[postEnd - 1]] ?: 0
        val rightTreeSize = inEnd - i - 1
        val newPostEnd = postEnd - 1
        node.left = helper(lookup, postorder, inorder, newPostEnd - rightTreeSize, inStart, i)
        node.right = helper(lookup, postorder, inorder, newPostEnd, i + 1, inEnd)
        return node
    }
}

fun main(args: Array<String>) {
    // LC OJ passed
    // see [ConstructBinaryTreeFromInorderAndPostorderTraversalTest] for unit test
}