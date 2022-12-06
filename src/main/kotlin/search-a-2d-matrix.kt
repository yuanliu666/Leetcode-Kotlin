/**
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix.
 * This matrix has the following properties:
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 */
class Solution_search_a_2d_matrix {

    // Flatten 2D array to 1D array and execute binary search
    // index = r*n+c
    // T:O(log(m*n)) S:O(1)
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        val m = matrix.size
        val n = matrix[0].size
        var lo = 0
        var hi = m * n - 1
        while (lo < hi) {
            val mid = (hi - lo) / 2 + lo
            when {
                getNumber(matrix, mid) == target -> return true
                getNumber(matrix, mid) > target -> hi = mid - 1
                else -> lo = mid + 1
            }
        }
        return getNumber(matrix, lo) == target
    }

    private fun getNumber(matrix: Array<IntArray>, idx: Int): Int {
        val m = matrix.size
        val n = matrix[0].size
        val r = idx / n
        val c = idx % n
        return matrix[r][c]
    }
}