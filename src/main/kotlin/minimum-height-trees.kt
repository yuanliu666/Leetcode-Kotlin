/**
 * A tree is an undirected graph in which any two vertices are connected by exactly one path.
 * In other words, any connected graph without simple cycles is a tree.
 * Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges
 * where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree,
 * you can choose any node of the tree as the root.
 * When you select a node x as the root, the result tree has height h.
 * Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).
 * Return a list of all MHTs' root labels. You can return the answer in any order.
 * The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 */
class Solution_minimum_height_trees {

    // https://leetcode.com/problems/minimum-height-trees/solutions/900035/minimum-height-trees/
    // Important observation is, those roots are max size of 2.
    // We can apply topological sort, each time remove out-most nodes with just 1 connection,
    // and stop once 2 or fewer nodes left, which will be the answer.
    // T:O(n), spent O(n + e) to build map, but because we cannot have cycle, O(e) = O(n)
    // Then we iterate nodes at most once, so it's still O(n).
    // S:O(n), again we won't have cycle so a node can only have that many connections.
    fun findMinHeightTrees(n: Int, edges: Array<IntArray>): List<Int> {
        if (n <= 1) {
            return mutableListOf<Int>().also {
                for (i in 0 until n) it.add(i)
            }
        }

        val map = hashMapOf<Int, MutableList<Int>>()
        for (i in 0 until n) map[i] = mutableListOf()
        for (e in edges) {
            map[e[0]]?.add(e[1])
            map[e[1]]?.add(e[0])
        }

        var cnt = n
        // Get start points
        val points = mutableListOf<Int>()
        for (i in 0 until n) {
            if (map[i]?.size == 1) {
                points.add(i)
            }
        }

        while (cnt > 2) {
            val next = mutableListOf<Int>()
            for (p in points) {
                val q: Int = map[p]!!.first()
                map.remove(p)
                cnt--
                map[q]?.remove(p)
                if ((map[q]?.size ?: 0) <= 1) {
                    next.add(q)
                }
            }
            points.clear()
            points.addAll(next)
        }
        return map.keys.toList()
    }
}