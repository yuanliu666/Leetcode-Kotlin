/**
 * Given the root of a binary tree, the value of a target node target, and an integer k,
 * return an array of the values of all nodes that have a distance k from the target node.
 * You can return the answer in any order.
 */
class Solution_all_nodes_distance_k_in_binary_tree {

    // Convert tree to graph and do BFS.
    // T:O(n) as construct phase is O(n) which iterates whole tree. For BFS phase it is still O(n)
    // as each tree node can have at most 3 edges and at most iterate whole tree.
    // S:O(n).
    fun distanceK(root: TreeNode?, target: TreeNode?, k: Int): List<Int> {
        if (root == null || target == null) return emptyList()
        if (k == 0) return listOf(target.`val`)
        val map = hashMapOf<Int, MutableList<Int>>()
        constructGraph(root, map)
        val lv = mutableListOf(target.`val`)
        val visitedMap = hashMapOf(target.`val` to true)
        var dist = 0
        while (lv.isNotEmpty()) {
            val nextLv = mutableListOf<Int>()
            for (i in lv) {
                for (j in map[i]!!) {
                    if (visitedMap[j] != true) {
                        visitedMap[j] = true
                        nextLv.add(j)
                    }
                }
            }
            dist++
            if (dist == k) return nextLv
            lv.clear()
            lv.addAll(nextLv)
        }
        return emptyList()
    }

    private fun constructGraph(root: TreeNode, map: HashMap<Int, MutableList<Int>>) {
        map.putIfAbsent(root.`val`, mutableListOf())
        val left = root.left
        val right = root.right
        if (left != null) {
            map[left.`val`] = mutableListOf(root.`val`)
            map[root.`val`]?.add(left.`val`)
            constructGraph(left, map)
        }
        if (right != null) {
            map[right.`val`] = mutableListOf(root.`val`)
            map[root.`val`]?.add(right.`val`)
            constructGraph(right, map)
        }
    }

    // On a second thought, just knowing parent is enough for BFS purpose.
    // Same complexity, but it runs slower for some reasons.
    fun distanceK2(root: TreeNode?, target: TreeNode?, k: Int): List<Int> {
        if (root == null || target == null) return emptyList()
        if (k == 0) return listOf(target.`val`)
        val pMap = hashMapOf<Int, TreeNode>()
        constructParentMap(root, pMap)

        val lv = mutableListOf(target)
        val visitedMap = hashMapOf(target.`val` to true)
        var dist = 0
        while (lv.isNotEmpty()) {
            val nextLv = mutableListOf<TreeNode>()
            for (node in lv) {
                for (nextNode in listOf(node.left, node.right, pMap[node.`val`])) {
                    if (nextNode != null && visitedMap[nextNode.`val`] != true) {
                        visitedMap[nextNode.`val`] = true
                        nextLv.add(nextNode)
                    }
                }
            }
            dist++
            if (dist == k) return nextLv.map { it.`val` }
            lv.clear()
            lv.addAll(nextLv)
        }
        return emptyList()
    }

    private fun constructParentMap(root: TreeNode, map: MutableMap<Int, TreeNode>) {
        root.left?.let {
            map[it.`val`] = root
            constructParentMap(it, map)
        }
        root.right?.let {
            map[it.`val`] = root
            constructParentMap(it, map)
        }
    }
}