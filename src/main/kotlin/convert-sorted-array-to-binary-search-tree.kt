/**
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 * For this problem, a height-balanced binary tree is defined as
 * a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 *
 * Example:
 *
 * Given the sorted array: [-10,-3,0,5,9],
 *
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *
 *      0
 *     / \
 *   -3   9
 *   /   /
 *  -10  5
 */

import utils.TreeNode

class Solution_convert_sorted_array_to_binary_search_tree {

    // T:O(n) S:O(logn)
    fun sortedArrayToBST(nums: IntArray): TreeNode? {
        return helper(nums, 0, nums.size)
    }

    // start is inclusive, end is exclusive
    private fun helper(nums: IntArray, start: Int, end: Int): TreeNode? {
        if (start >= end) {
            return null
        }

        val mid = start.shr(1) + end.shr(1)
        val root = TreeNode(nums[mid])
        root.left = helper(nums, start, mid)
        root.right = helper(nums, mid + 1, end)
        return root
    }
}

fun main(args: Array<String>) {
    // LC OJ Passed
    // see [ConvertSortedArrayToBinarySearchTreeTest] for test
}