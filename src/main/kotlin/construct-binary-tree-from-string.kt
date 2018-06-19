import utils.TreeNode

/**
 * You need to construct a binary tree from a string consisting of parenthesis and integers.
 *
 * The whole input represents a binary tree.
 * It contains an integer followed by zero, one or two pairs of parenthesis.
 * The integer represents the root's value
 * and a pair of parenthesis contains a child binary tree with the same structure.
 *
 * You always start to construct the left child node of the parent first if it exists.
 *
 * Example:
 *
 * Input: "4(2(3)(1))(6(5))"
 * Output: return the tree root node representing the following tree:
 *
 *      4
 *    /   \
 *   2     6
 *  / \   /
 * 3   1 5
 *
 *
 * Note:
 *
 * 1. There will only be '(', ')', '-' and '0' ~ '9' in the input string.
 * 2. An empty tree is represented by "" instead of "()".
 */

class Solution_construct_binary_tree_from_string {

    // T:O(n) S:O(h)
    fun constructTree(s: String): TreeNode? {
        return helper(s, 0, s.length)
    }

    private fun helper(s: String, start: Int, end: Int): TreeNode? {
        if (start >= end) {
            return null
        }
        // find left sub tree
        var leftStartIdx = -1
        var leftEndIdx = -1
        var level = 0
        for (i in start until end) {
            if (s[i] == '(') {
                if (leftStartIdx == -1) {
                    leftStartIdx = i
                }
                level++
            } else if (s[i] == ')') {
                level--
                if (level == 0) {
                    leftEndIdx = i
                    break
                }
            }
        }
        when {
            leftStartIdx == -1 -> {
                // no '(' encountered, return TreeNode directly
                return TreeNode(s.substring(start, end).toInt())
            }
            leftEndIdx + 1 == end -> {
                // no right sub tree
                val root = TreeNode(s.substring(start, leftStartIdx).toInt())
                root.left = helper(s, leftStartIdx + 1, leftEndIdx)
                return root
            }
            else -> {
                val root = TreeNode(s.substring(start, leftStartIdx).toInt())
                root.left = helper(s, leftStartIdx + 1, leftEndIdx)
                root.right = helper(s, leftEndIdx + 2, end - 1)
                return root
            }
        }
    }

    fun constructTree2(s: String): TreeNode? {
        return helper2(s, 0).first
    }

    private fun helper2(s: String, start: Int): Pair<TreeNode?, Int> {
        var p = start
        // get root
        if (s[p] == '-') {
            p++
        }
        while (p < s.length && s[p].isDigit()) {
            p++
        }
        val root = TreeNode(s.substring(start, p).toInt())
        // look at next bracket, should be left sub tree if present
        if (p < s.length && s[p] == '(') {
            p++
            val temp = helper2(s, p)
            root.left = temp.first
            p = temp.second
            p++
        }
        // then look at right sub tree bracket if present
        if (p < s.length && s[p] == '(') {
            p++
            val temp = helper2(s, p)
            root.right = temp.first
            p = temp.second
            p++
        }
        return Pair(root, p)
    }
}

fun main(args: Array<String>) {
    // see [ConstructBinaryTreeFromStringTest] for unit test
}