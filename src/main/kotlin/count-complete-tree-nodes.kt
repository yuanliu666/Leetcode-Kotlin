/**
 * Given a complete binary tree, count the number of nodes.
 *
 * Note:
 *
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last,
 * is completely filled, and all nodes in the last level are as far left as possible.
 * It can have between 1 and 2^h nodes inclusive at the last level h.
 *
 * Example:
 *
 * Input:
 *     1
 *    / \
 *   2   3
 *  / \  /
 * 4  5 6
 *
 * Output: 6
 */

import utils.TreeNode

class Solution_count_complete_tree_nodes {

    // T:O(n) S:O(h)
    // simple solution just to count all nodes
    private var cnt = 0

    fun countNodes(root: TreeNode?): Int {
        cnt = 0
        helper(root)
        return cnt
    }

    private fun helper(node: TreeNode?) {
        node?.let {
            cnt++
            helper(node.left)
            helper(node.right)
        }
    }


    // T:O((logn)^2) S:O(1)
    // binary search solution
    // first get level h (starting from 0 at root) by finding most left leaf
    // then use binary search on last level to find out how many leaves are there
    fun countNodes2(root: TreeNode?): Int {
        if (root == null) {
            return 0
        }
        // get max level h
        var h = 0
        var node = root
        while (node?.left != null) {
            node = node.left
            h++
        }
        // index node from 1 ~ [2^(h+1)-1], so last index is result
        // getting 2^h
        var twoPowh = 1
        var cnt = 0
        while (cnt < h) {
            twoPowh = twoPowh.shl(1)
            cnt++
        }
        // left is inclusive and right is exclusive
        var left = twoPowh
        var right = twoPowh.shl(1)
        while (left < right) {
            val mid = left + (right - left).shr(1)
            if (isNodeExist(root, mid)) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        return left - 1
    }

    // T:O(logn)
    // here we only consider nodes on last level
    // value is from 2^h ~ 2^(h+1)-1, either way it can be represented in h+1 bit binary
    // from high to low, first 1 represents root, and following 0 means go left and 1 means go right
    // example: 6 = 0b110 means from root go right then go left
    private fun isNodeExist(root: TreeNode?, n: Int): Boolean {
        // align k to one bit lower of highest, by first go over highest by 1 and then go back 2
        // example: 110
        //           ^
        var k = 1
        while (k <= n) {
            k = k.shl(1)
        }
        k = k.shr(2)
        var node = root
        while (k > 0) {
            node = if (n and k == 0) {
                node?.left
            } else {
                node?.right
            }
            k = k.shr(1)
        }
        return node != null
    }
}

fun main(args: Array<String>) {
    // LC OJ Passed
    // see [CountCompleteTreeNodesTest] for unit test
}