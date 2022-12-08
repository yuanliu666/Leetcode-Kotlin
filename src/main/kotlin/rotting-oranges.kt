/**
 * You are given an m x n grid where each cell can have one of three values:
 * 0 representing an empty cell,
 * 1 representing a fresh orange, or
 * 2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange.
 * If this is impossible, return -1.
 */
class Solution_rotting_oranges {

    // BFS. T:O(m*n) S:O(m*n)
    fun orangesRotting(grid: Array<IntArray>): Int {
        val dir = listOf(listOf(1, 0), listOf(-1, 0), listOf(0, 1), listOf(0, -1))
        val m = grid.size
        val n = grid[0].size
        // Get first batch of sources
        val list = mutableListOf<IntArray>()
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (grid[i][j] == 2) {
                    list.add(intArrayOf(i, j))
                }
            }
        }
        // Spread by steps
        var ret = 0
        while (list.isNotEmpty()) {
            val next = mutableListOf<IntArray>()
            for (arr in list) {
                for (d in dir) {
                    val r = arr[0] + d[0]
                    val c = arr[1] + d[1]
                    if (r in 0 until m && c in 0 until n && grid[r][c] == 1) {
                        next.add(intArrayOf(r, c))
                        grid[r][c] = 2
                    }
                }
            }
            // If next is empty, the last round doesn't actually spread
            if (next.isNotEmpty()) ret++
            list.clear()
            list.addAll(next)
        }
        // Check if any fresh oranges remaining
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (grid[i][j] == 1) return -1
            }
        }
        return ret
    }
}