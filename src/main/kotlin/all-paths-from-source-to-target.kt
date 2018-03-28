/**
 * Given a directed, acyclic graph of N nodes.
 * Find all possible paths from node 0 to node N-1, and return them in any order.
 *
 * The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.
 * graph\[i] is a list of all nodes j for which the edge (i, j) exists.
 *
 * Example:
 * Input: [[1,2], [3], [3], []]
 * Output: [[0,1,3],[0,2,3]]
 * Explanation: The graph looks like this:
 * 0--->1
 * |    |
 * v    v
 * 2--->3
 * There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
 * Note:
 *
 * The number of nodes in the graph will be in the range [2, 15].
 * You can print different paths in any order, but you should keep the order of nodes inside one path.
 */

class Solution_all_paths_from_source_to_target {

    // Time:  O(p + r * n), p is the count of all the possible paths in graph, r is the count of the result.
    // Space: O(n)

    // recursive DFS, slower
    fun allPathsSourceTarget(graph: Array<IntArray>): List<List<Int>> {
        val ret = mutableListOf<List<Int>>()
        helper(0, graph, mutableListOf(0), ret)
        return ret
    }

    fun helper(cur: Int, graph: Array<IntArray>, temp: MutableList<Int>, ret: MutableList<List<Int>>) {
        if (cur == graph.size - 1) {
            ret.add(temp.toList())
            return
        }
        for (i in graph[cur]) {
            temp.add(i)
            helper(i, graph, temp, ret)
            temp.remove(i)
        }
    }

    // iterative stack, faster
    fun allPathsSourceTarget2(graph: Array<IntArray>): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        val stack = java.util.Stack<List<Int>>()
        stack.add(listOf(0))
        while (!stack.isEmpty()) {
            val path = stack.pop()
            val lastNode = path[path.size - 1]
            if (lastNode == graph.size - 1) {
                res.add(path.toList())
            } else {
                for (node in graph[lastNode]) {
                    stack.push(path.toMutableList().plus(node))
                }
            }
        }
        return res
    }
}

fun main(args: Array<String>) {
    // LC OJ Passed
    val s = Solution_all_paths_from_source_to_target()
    print(s.allPathsSourceTarget(arrayOf(intArrayOf(1, 2), intArrayOf(3), intArrayOf(3), intArrayOf())))
    print(s.allPathsSourceTarget2(arrayOf(intArrayOf(1, 2), intArrayOf(3), intArrayOf(3), intArrayOf())))
}