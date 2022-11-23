/**
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 */
class Solution_numberof_islands {

    private val dir = listOf(listOf(1, 0), listOf(0, 1), listOf(0, -1), listOf(-1, 0))

    // T:O(m*n) S:O(m*n)
    fun numIslands(grid: Array<CharArray>): Int {
        var ret = 0
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (grid[i][j] == '1') {
                    ret++
                    bfs(grid, i, j)
                }
            }
        }
        return ret
    }

    private fun bfs(grid: Array<CharArray>, r: Int, c: Int) {
        val m = grid.size
        val n = grid[0].size
        val q = LinkedList<List<Int>>()
        q.add(listOf(r, c))

        while (q.isNotEmpty()) {
            val cur = q.poll()
            grid[cur[0]][cur[1]] = '0'
            for (d in dir) {
                val nr = cur[0] + d[0]
                val nc = cur[1] + d[1]
                if (nr in 0 until m && nc in 0 until n && grid[nr][nc] == '1') {
                    q.offer(listOf(nr, nc))
                    grid[nr][nc] = '0'
                }
            }
        }
    }
}