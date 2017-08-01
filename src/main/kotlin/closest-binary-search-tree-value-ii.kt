/**
 * Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
 *
 * Note:
 *
 * Given target value is a floating point.
 * You may assume k is always valid, that is: k ≤ total nodes.
 * You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
 *
 *
 * Follow up:
 * Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
 *
 * Hint:
 *
 * 1. Consider implement these two helper functions:
 * 　i. getPredecessor(N), which returns the next smaller node to N.
 * 　ii. getSuccessor(N), which returns the next larger node to N.
 * 2. Try to assume that each node has a parent pointer, it makes the problem much easier.
 * 3. Without parent pointer we just need to keep track of the path from the root to the current node using a stack.
 * 4. You would need two stacks to track the path in finding predecessor and successor node separately.
 */

import utils.TreeNode
import java.util.*


class Solution_closest_binary_search_tree_value_ii {
    // Reference: https://discuss.leetcode.com/topic/22940/ac-clean-java-solution-using-two-stacks

    // T:O(n+k) S:O(n)
    fun closestKValues(root: TreeNode, target: Double, k: Int): List<Int> {
        var k1 = k
        val res = ArrayList<Int>()

        val s1 = Stack<Int>() // predecessors
        val s2 = Stack<Int>() // successors

        inorder(root, target, false, s1)
        inorder(root, target, true, s2)

        while (k1-- > 0) {
            if (s1.isEmpty())
                res.add(s2.pop())
            else if (s2.isEmpty())
                res.add(s1.pop())
            else if (Math.abs(s1.peek() - target) < Math.abs(s2.peek() - target))
                res.add(s1.pop())
            else
                res.add(s2.pop())
        }

        return res
    }

    // inorder traversal
    fun inorder(root: TreeNode?, target: Double, reverse: Boolean, stack: Stack<Int>) {
        if (root == null) return

        inorder(if (reverse) root.right else root.left, target, reverse, stack)
        // early terminate, no need to traverse the whole tree
        if (reverse && root.value <= target || !reverse && root.value > target) return
        // track the value of current node
        stack.push(root.value)
        inorder(if (reverse) root.left else root.right, target, reverse, stack)
    }


    // A more efficient solution
    // T:O( log(n) + k) S:O(n)
    fun closestKValues2(root: TreeNode, target: Double, k: Int): List<Int> {
        var k1 = k
        val ret = LinkedList<Int>()
        val succ = Stack<TreeNode>()
        val pred = Stack<TreeNode>()
        initializePredecessorStack(root, target, pred)
        initializeSuccessorStack(root, target, succ)
        if (!succ.isEmpty() && !pred.isEmpty() && succ.peek().value == pred.peek().value) {
            getNextPredecessor(pred)
        }
        while (k1-- > 0) {
            if (succ.isEmpty()) {
                ret.add(getNextPredecessor(pred))
            } else if (pred.isEmpty()) {
                ret.add(getNextSuccessor(succ))
            } else {
                val succ_diff = Math.abs(succ.peek().value - target)
                val pred_diff = Math.abs(pred.peek().value - target)
                if (succ_diff < pred_diff) {
                    ret.add(getNextSuccessor(succ))
                } else {
                    ret.add(getNextPredecessor(pred))
                }
            }
        }
        return ret
    }

    private fun initializeSuccessorStack(root: TreeNode?, target: Double, succ: Stack<TreeNode>) {
        var treeNode = root
        while (treeNode != null) {
            if (treeNode.value.toDouble() == target) {
                succ.push(treeNode)
                break
            } else if (treeNode.value > target) {
                succ.push(treeNode)
                treeNode = treeNode.left
            } else {
                treeNode = treeNode.right
            }
        }
    }

    private fun initializePredecessorStack(root: TreeNode?, target: Double, pred: Stack<TreeNode>) {
        var treeNode = root
        while (treeNode != null) {
            if (treeNode.value.toDouble() == target) {
                pred.push(treeNode)
                break
            } else if (treeNode.value < target) {
                pred.push(treeNode)
                treeNode = treeNode.right
            } else {
                treeNode = treeNode.left
            }
        }
    }

    private fun getNextSuccessor(succ: Stack<TreeNode>): Int {
        var curr: TreeNode? = succ.pop()
        val ret = curr!!.value
        curr = curr.right
        while (curr != null) {
            succ.push(curr)
            curr = curr.left
        }
        return ret
    }

    private fun getNextPredecessor(pred: Stack<TreeNode>): Int {
        var curr: TreeNode? = pred.pop()
        val ret = curr!!.value
        curr = curr.left
        while (curr != null) {
            pred.push(curr)
            curr = curr.right
        }
        return ret
    }
}

fun main(args: Array<String>) {
    val s = Solution_closest_binary_search_tree_value_ii()
    val r = TreeNode(4)
    r.right = TreeNode(5)
    r.left = TreeNode(2)
    r.left?.left = TreeNode(1)
    r.left?.right = TreeNode(3)
    println(s.closestKValues(r, 2.2, 3))
    println(s.closestKValues2(r, 2.2, 3))
}