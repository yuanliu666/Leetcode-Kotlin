/**
 * Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root.
 * Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.
 *
 * Left boundary is defined as the path from root to the left-most node.
 * Right boundary is defined as the path from root to the right-most node.
 * If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary.
 * Note this definition only applies to the input binary tree, and not applies to any subtrees.
 *
 * The left-most node is defined as a leaf node you could reach
 * when you always firstly travel to the left subtree if exists.
 * If not, travel to the right subtree. Repeat until you reach a leaf node.
 *
 * The right-most node is also defined by the same way with left and right exchanged.
 *
 * Example 1
 *
 * Input:
 *
 * 1
 *  \
 *   2
 *  / \
 * 3   4
 * Output:
 * [1, 3, 4, 2]
 *
 * Explanation:
 * The root doesn't have left subtree, so the root itself is left boundary.
 * The leaves are node 3 and 4.
 * The right boundary are node 1,2,4. Note the anti-clockwise direction means
 * you should output reversed right boundary.
 * So order them in anti-clockwise without duplicates and we have [1,3,4,2].
 *
 * Example 2
 *
 * Input:
 *
 *     ____1_____
 *    /          \
 *   2            3
 *  / \          /
 * 4   5        6
 *    / \      / \
 *   7   8    9  10
 *
 * Output:
 * [1,2,4,7,8,9,10,6,3]
 *
 * Explanation:
 * The left boundary are node 1,2,4. (4 is the left-most node according to definition)
 * The leaves are node 4,7,8,9,10.
 * The right boundary are node 1,3,6,10. (10 is the right-most node).
 * So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].
 */

import utils.TreeNode

class Solution_boundary_of_binary_tree {
    // The key is how to avoid duplicates.
    // If root has two sub trees, the left boundary and right boundary can only share the root.
    // And the left boundary and right boundary can only share one node with leaves each.
    // A proper choice is to isolate root and leave out left most node right most node to leaves.

    // T:O(n) S:O(n)
    fun getBoundary(root: TreeNode): List<Int> {
        val nodes = mutableListOf(root.value)
        getLeftBoundaryExceptLeaf(root.left, nodes)
        getLeaves(root.left, nodes)
        getLeaves(root.right, nodes)
        getRightBoundaryExceptLeaf(root.right, nodes)
        return nodes
    }

    fun getLeftBoundaryExceptLeaf(root: TreeNode?, nodes: MutableList<Int>) {
        if (root == null || (root.left == null && root.right == null)) {
            return
        }
        nodes.add(root.value)
        if (root.left != null) {
            getLeftBoundaryExceptLeaf(root.left, nodes)
        } else {
            getLeftBoundaryExceptLeaf(root.right, nodes)
        }
    }

    fun getRightBoundaryExceptLeaf(root: TreeNode?, nodes: MutableList<Int>) {
        if (root == null || (root.left == null && root.right == null)) {
            return
        }
        if (root.right != null) {
            getRightBoundaryExceptLeaf(root.right, nodes)
        } else {
            getRightBoundaryExceptLeaf(root.left, nodes)
        }
        nodes.add(root.value)
    }

    fun getLeaves(root: TreeNode?, nodes: MutableList<Int>) {
        if (root == null) {
            return
        }
        if (root.left == null && root.right == null) {
            nodes.add(root.value)
        }
        root.left?.let { getLeaves(it, nodes) }
        root.right?.let { getLeaves(it, nodes) }
    }
}

fun main(args: Array<String>) {
    val s = Solution_boundary_of_binary_tree()
    println(s.getBoundary(TreeNode(0)))
    val r1 = TreeNode(1)
    r1.right = TreeNode(2)
    r1.right?.left = TreeNode(3)
    r1.right?.right = TreeNode(4)
    println(s.getBoundary(r1))
    val r2 = TreeNode(1)
    r2.left = TreeNode(2)
    r2.right = TreeNode(3)
    r2.left?.left = TreeNode(4)
    r2.left?.right = TreeNode(5)
    r2.right?.left = TreeNode(6)
    r2.left?.right?.left = TreeNode(7)
    r2.left?.right?.right = TreeNode(8)
    r2.right?.left?.left = TreeNode(9)
    r2.right?.left?.right = TreeNode(10)
    println(s.getBoundary(r2))
}