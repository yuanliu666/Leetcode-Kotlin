/**
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 */
class Solution_spirial_matrix {

    // T:O(m*n) S:O(1) (returning space does not count as extra space)
    fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        val m = matrix.size
        val n = matrix[0].size
        val dir = listOf(listOf(0, 1), listOf(1, 0), listOf(0, -1), listOf(-1, 0))
        var d = 0
        var cur = intArrayOf(0, 0)
        val ret = mutableListOf<Int>()
        ret.add(matrix[cur[0]][cur[1]])
        mark(matrix, cur[0], cur[1])
        while (ret.size < m * n) {
            val r = cur[0] + dir[d][0]
            val c = cur[1] + dir[d][1]
            if (r in 0 until m && c in 0 until n && kotlin.math.abs(matrix[r][c]) < 1000) {
                ret.add(matrix[r][c])
                mark(matrix, r, c)
                cur = intArrayOf(r, c)
            } else {
                d = (d + 1) % 4
            }
        }
        return ret
    }

    // In place mark as visited
    private fun mark(matrix: Array<IntArray>, x: Int, y: Int) {
        if (matrix[x][y] < 0) {
            matrix[x][y] -= 1000
        } else {
            matrix[x][y] += 1000
        }
    }
}