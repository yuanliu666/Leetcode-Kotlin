/**
 * Given a binary tree, return all root-to-leaf paths.
 *
 * For example, given the following binary tree:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 * All root-to-leaf paths are:
 *
 * ["1->2->5", "1->3"]
 */
import utils.TreeNode

class Solution_binary_tree_paths {
    // T:O(n*h) S:O(h)
    fun dfs(root: TreeNode): List<String> {
        val ret = mutableListOf<String>()
        dfsRecur(root, "", ret)
        return ret
    }

    fun dfsRecur(cur: TreeNode, path: String, ret: MutableList<String>) {
        val nextPath: String
        if (path.isEmpty()) {
            // first node
            nextPath = cur.value.toString()
        } else {
            nextPath = "$path->${cur.value}"
        }
        when {
            cur.left == null && cur.right == null -> {
                ret.add(nextPath)
                return
            }
            else -> {
                cur.left?.let { dfsRecur(it, nextPath, ret) }
                cur.right?.let { dfsRecur(it, nextPath, ret) }
            }
        }
    }
}

fun main(args: Array<String>) {
    val root = TreeNode(1)
    root.left = TreeNode(2)
    root.right = TreeNode(3)
    root.left!!.right = TreeNode(5)
    println(Solution_binary_tree_paths().dfs(root))
}
