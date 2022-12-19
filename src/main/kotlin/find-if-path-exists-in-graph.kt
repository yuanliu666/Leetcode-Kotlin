/**
 * There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive).
 * The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi.
 * Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
 * You want to determine if there is a valid path that exists from vertex source to vertex destination.
 * Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.
 */
class Solution_find_if_path_exists_in_graph {

    class Union(n: Int) {
        private val p = IntArray(n) { i -> i }

        fun connect(i: Int, j: Int) {
            val a = find(i)
            val b = find(j)
            if (a != b) {
                p[a] = b
            }
        }

        fun find(i: Int): Int {
            var a = i
            while (p[a] != a) {
                p[a] = p[p[a]]
                a = p[a]
            }
            return a
        }
    }

    // Union find solution. Let m = edges.size, T:O(mlogn) S:O(n)
    fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
        val u = Union(n)
        for (e in edges) {
            u.connect(e[0], e[1])
        }
        return u.find(source) == u.find(destination)
    }
}