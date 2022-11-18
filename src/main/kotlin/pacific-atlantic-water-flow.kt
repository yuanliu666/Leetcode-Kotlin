import java.util.*
/**
 * There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean.
 * The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.
 * The island is partitioned into a grid of square cells.
 * You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).
 * The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west
 * if the neighboring cell's height is less than or equal to the current cell's height.
 * Water can flow from any cell adjacent to an ocean into the ocean.
 * Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that
 * rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.
 *
 * m == heights.length
 * n == heights[r].length
 * 1 <= m, n <= 200
 * 0 <= heights[r][c] <= 10^5
 */
class Solution_pacific_atlantic_water_flow {

    // This is very useful for writing less code
    private val dir = listOf(listOf(0, 1), listOf(1, 0), listOf(0, -1), listOf(-1, 0))

    // BFS solution, T:O(m*n) as each cell can be visited 4 times, S:O(m*n)
    fun pacificAtlantic(heights: Array<IntArray>): List<List<Int>> {
        val n = heights[0].size
        val m = heights.size
        // Use a bit mask to store answer, 0b1 means pacific, 0b10 means atlantic
        val arr = Array(m) { IntArray(n) }
        val visited1 = BooleanArray(m * n)
        val visited2 = BooleanArray(m * n)
        val q1 = LinkedList<Int>()
        val q2 = LinkedList<Int>()
        // Get borders
        for (i in heights.indices) {
            q1.offer(convert(i, 0, n))
            q2.offer(convert(i, n - 1, n))
            visited1[convert(i, 0, n)] = true
            visited2[convert(i, n - 1, n)] = true
            arr[i][0] = arr[i][0] or 1
            arr[i][n - 1] = arr[i][n - 1] or 2
        }
        for (j in heights[0].indices) {
            q1.offer(convert(0, j, n))
            q2.offer(convert(m - 1, j, n))
            visited1[convert(0, j, n)] = true
            visited2[convert(m - 1, j, n)] = true
            arr[0][j] = arr[0][j] or 1
            arr[m - 1][j] = arr[m - 1][j] or 2
        }
        // BFS
        bfs(q1, heights, arr, 1, visited1)
        bfs(q2, heights, arr, 2, visited2)
        // Get result
        val result = mutableListOf<List<Int>>()
        for (i in arr.indices) {
            for (j in arr[0].indices) {
                if (arr[i][j] == 3) {
                    result.add(listOf(i, j))
                }
            }
        }
        return result
    }

    private fun bfs(q: LinkedList<Int>, heights: Array<IntArray>, arr: Array<IntArray>, mask: Int, visited: BooleanArray) {
        val m = heights.size
        val n = heights[0].size
        while (q.isNotEmpty()) {
            val e = q.poll()
            val curRow = e / n
            val curCol = e % n
            for (d in dir) {
                val r = curRow + d[0]
                val c = curCol + d[1]
                if (r in 0 until m && c in 0 until n && heights[r][c] >= heights[curRow][curCol]
                    && !visited[convert(r, c, n)]
                ) {
                    q.offer(convert(r, c, n))
                    visited[convert(r, c, n)] = true
                    arr[r][c] = arr[r][c] or mask
                }
            }
        }
    }

    private fun convert(r: Int, c: Int, n: Int): Int {
        return n * r + c
    }

    // DFS, same complexity
    fun pacificAtlanticDFS(heights: Array<IntArray>): List<List<Int>> {
        val n = heights[0].size
        val m = heights.size
        // Use a bit mask to store answer, 0b1 means pacific, 0b10 means atlantic
        val arr = Array(m) { IntArray(n) }
        val visited1 = BooleanArray(m * n)
        val visited2 = BooleanArray(m * n)

        for (i in heights.indices) {
            arr[i][0] = arr[i][0] or 1
            arr[i][n - 1] = arr[i][n - 1] or 2
            dfs(i, 0, heights, arr, 1, visited1)
            dfs(i, n - 1, heights, arr, 2, visited2)
        }
        for (j in heights[0].indices) {
            arr[0][j] = arr[0][j] or 1
            arr[m - 1][j] = arr[m - 1][j] or 2
            dfs(0, j, heights, arr, 1, visited1)
            dfs(m - 1, j, heights, arr, 2, visited2)
        }

        val result = mutableListOf<List<Int>>()
        for (i in arr.indices) {
            for (j in arr[0].indices) {
                if (arr[i][j] == 3) {
                    result.add(listOf(i, j))
                }
            }
        }
        return result
    }

    private fun dfs(curRow: Int, curCol: Int, heights: Array<IntArray>, arr: Array<IntArray>, mask: Int, visited: BooleanArray) {
        val m = heights.size
        val n = heights[0].size
        if (visited[convert(curRow, curCol, n)]) return
        visited[convert(curRow, curCol, n)] = true
        for (d in dir) {
            val r = curRow + d[0]
            val c = curCol + d[1]
            if (r in 0 until m && c in 0 until n && heights[r][c] >= heights[curRow][curCol]
                && !visited[convert(r, c, n)]
            ) {
                arr[r][c] = arr[r][c] or mask
                dfs(r, c, heights, arr, mask, visited)
            }
        }
    }
}