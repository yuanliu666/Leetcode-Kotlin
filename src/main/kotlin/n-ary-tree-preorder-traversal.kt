/**
 * Given the root of an n-ary tree, return the preorder traversal of its nodes' values.
 * Nary-Tree input serialization is represented in their level order traversal.
 * Each group of children is separated by the null value
 */
class Solution_n_ary_tree_preorder_traversal {

    // Recursive, T:O(n) S:O(n)
    fun preorder(root: Node?): List<Int> {
        val ret = mutableListOf<Int>()
        recur(root, ret)
        return ret
    }

    private fun recur(n: Node?, ret: MutableList<Int>) {
        if (n != null) {
            ret.add(n.`val`)
            for (child in n.children) {
                recur(child, ret)
            }
        }
    }

    // Iterative, T:O(n) S:O(n)
    fun preorderIterative(root: Node?): List<Int> {
        val ret = mutableListOf<Int>()
        if (root == null) return ret
        val stk = Stack<Node>()
        stk.add(root)

        while (stk.isNotEmpty()) {
            val n = stk.pop()
            ret.add(n.`val`)
            for (i in n.children.indices.reversed()) {
                stk.push(n.children[i])
            }
        }
        return ret
    }
}