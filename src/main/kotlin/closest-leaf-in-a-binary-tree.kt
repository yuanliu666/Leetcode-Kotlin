/**
 * Given a binary tree where every node has a unique value, and a target key k,
 * find the value of the closest leaf node to target k in the tree.
 *
 * Here, closest to a leaf means the least number of edges travelled on the binary tree to reach
 * any leaf of the tree. Also, a node is called a leaf if it has no children.
 *
 * In the following examples, the input tree is represented in flattened form row by row.
 * The actual root tree given will be a TreeNode object.
 *
 * Example 1:
 * Input:
 * root = [1, 3, 2], k = 1
 * Diagram of binary tree:
 *           1
 *          / \
 *         3   2
 *
 * Output: 2 (or 3)
 *
 * Explanation: Either 2 or 3 is the closest leaf node to the target of 1.
 *
 * Example 2:
 * Input:
 * root = [1], k = 1
 * Output: 1
 *
 * Explanation: The closest leaf node is the root node itself.
 *
 * Example 3:
 * Input:
 * root = [1,2,3,4,null,null,null,5,null,6], k = 2
 * Diagram of binary tree:
 *              1
 *             / \
 *            2   3
 *           /
 *          4
 *         /
 *        5
 *       /
 *      6
 *
 * Output: 3
 * Explanation: The leaf node with value 3 (and not the leaf node with value 6) is closest to the node with value 2.
 *
 * Note:
 * - root represents a binary tree with at least 1 node and at most 1000 nodes.
 * - Every node has a unique node.val in range [1, 1000].
 * - There exists some node in the given binary tree for which node.val == k.
 */

import utils.TreeNode

class Solution_closest_leaf_in_a_binary_tree {

    // if we can go BFS starting from node k and step out until hit a leaf, that is the result
    // but there is a problem: we need way to traverse from bottom to top
    // to make this happen, we need create a map of `neighbors`, including direct parent and children

    // note it's a little bit tricky when deciding to use either TreeNode or Int as key
    // we want to start with node k, so use Int as key for neighbors map is OK
    // then it's not so easy to tell if this Int represents a leaf or not
    // so we might as well also use a set to store leaves values
    // another approach is to use TreeNode as key, then we need a global parameter to save node k to start with
    // but then we do not bother to store leaves, instead just check its children

    // T:O(n) S:O(n)
    fun getClosestLeaf(root: TreeNode, k: Int): Int {
        val map: MutableMap<Int, MutableList<TreeNode>> = mutableMapOf()
        val leavesSet: MutableSet<Int> = mutableSetOf()
        generateNeighborsMap(root, map, leavesSet)

        val bfsQueue = mutableListOf(k)
        val lookupSet = mutableSetOf(k)
        val nextQueue: MutableList<Int> = mutableListOf()
        while (bfsQueue.isNotEmpty()) {
            nextQueue.clear()
            for (v in bfsQueue) {
                if (v in leavesSet) {
                    return v
                }
                for (n in map[v] ?: mutableListOf()) {
                    if (n.value in lookupSet) {
                        continue
                    }
                    lookupSet.add(n.value)
                    nextQueue.add(n.value)
                }
            }
            bfsQueue.clear()
            bfsQueue.addAll(nextQueue)
        }
        return 0
    }

    private fun generateNeighborsMap(node: TreeNode,
                                     neighborsMap: MutableMap<Int, MutableList<TreeNode>>,
                                     leavesSet: MutableSet<Int>) {
        if (node.isLeaf()) {
            leavesSet.add(node.value)
            return
        }
        node.left?.let {
            neighborsMap.putIfAbsent(node.value, mutableListOf())
            neighborsMap[node.value]?.add(it)
            neighborsMap.putIfAbsent(it.value, mutableListOf())
            neighborsMap[it.value]?.add(node)
            generateNeighborsMap(it, neighborsMap, leavesSet)
        }
        node.right?.let {
            neighborsMap.putIfAbsent(node.value, mutableListOf())
            neighborsMap[node.value]?.add(it)
            neighborsMap.putIfAbsent(it.value, mutableListOf())
            neighborsMap[it.value]?.add(node)
            generateNeighborsMap(it, neighborsMap, leavesSet)
        }
    }

    private fun TreeNode.isLeaf() = this.left == null && this.right == null
}

fun main(args: Array<String>) {
    val s = Solution_closest_leaf_in_a_binary_tree()

    val root1 = TreeNode(1)
    root1.left = TreeNode(3)
    root1.right = TreeNode(2)
    println(s.getClosestLeaf(root1, 1))

    println(s.getClosestLeaf(TreeNode(1), 1))

    val root2 = TreeNode(1)
    root2.right = TreeNode(3)
    root2.left = TreeNode(2)
    root2.left?.left = TreeNode(4)
    root2.left?.left?.left = TreeNode(5)
    root2.left?.left?.left?.left = TreeNode(6)
    println(s.getClosestLeaf(root2, 2))
}