/**
 * You need to construct a string consists of parenthesis and integers
 * from a binary tree with the preorder traversing way.
 *
 * The null node needs to be represented by empty parenthesis pair "()".
 * And you need to omit all the empty parenthesis pairs
 * that don't affect the one-to-one mapping relationship between the string and the original binary tree.
 *
 * Example 1:
 * Input: Binary tree: [1,2,3,4]
 *      1
 *    /   \
 *   2     3
 *  /
 * 4
 *
 * Output: "1(2(4))(3)"
 *
 * Explanation: Originally it needs to be "1(2(4)())(3()())",
 * but you need to omit all the unnecessary empty parenthesis pairs.
 * And it will be "1(2(4))(3)".
 *
 * Example 2:
 * Input: Binary tree: [1,2,3,null,4]
 *    1
 *  /   \
 * 2     3
 * \
 *  4
 *
 * Output: "1(2()(4))(3)"
 *
 * Explanation: Almost the same as the first example,
 * except we can't omit the first parenthesis pair to break the one-to-one mapping relationship
 * between the input and the output.
 */

import utils.TreeNode

class Solution_construct_string_from_binary_tree {

    // T:O(n) S:O(h)
    fun tree2str(t: TreeNode?): String =
            if (t == null) {
                ""
            } else {
                when {
                    t.left == null && t.right == null -> t.value.toString()
                    t.left == null -> "${t.value}()(${tree2str(t.right)})"
                    t.right == null -> "${t.value}(${tree2str(t.left)})"
                    else -> "${t.value}(${tree2str(t.left)})(${tree2str(t.right)})"
                }
            }

}

fun main(args: Array<String>) {
    // LC OJ passed,
    // note that LeetCode uses [treeNode.`val`] instead of [treeNode.value]
    val s = Solution_construct_string_from_binary_tree()
    val r1 = TreeNode(1)
    r1.left = TreeNode(2)
    r1.right = TreeNode(3)
    r1.left?.left = TreeNode(4)
    println(s.tree2str(r1))

    val r2 = TreeNode(1)
    r2.left = TreeNode(2)
    r2.right = TreeNode(3)
    r2.left?.right = TreeNode(4)
    println(s.tree2str(r2))
}