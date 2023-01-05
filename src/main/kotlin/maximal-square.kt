/**
 * Given an m x n binary matrix filled with 0's and 1's,
 * find the largest square containing only 1's and return its area.
 */
class Solution_maximal_square {

    // DP solution. Consider the bottom right corner of square, to form a new square,
    // it needs 3 part: left row, top row, and another square on top left.
    // So let dp[i][j] denotes max square width with matrix[i][j] as bottom right element,
    // dp[i][j]=min(dp[i-1][j],dp[i][j-1],dp[i-1][j-1])+1。
    // Drawing a graph can help understand it better, but it needs a min width
    // to make sure 3 parts all exist to form a square.
    // T:O(m*n) S:O(m*n)
    fun maximalSquare(matrix: Array<CharArray>): Int {
        var ret = 0
        val m = matrix.size
        val n = matrix[0].size
        val arr = Array(m) { IntArray(n) }
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (matrix[i][j] == '0') continue
                when {
                    i == 0 || j == 0 -> {
                        arr[i][j] = 1
                        ret = kotlin.math.max(ret, 1)
                    }

                    else -> {
                        val left = arr[i][j - 1]
                        val top = arr[i - 1][j]
                        val topLeft = arr[i - 1][j - 1]
                        val w = kotlin.math.min(kotlin.math.min(left, top), topLeft)
                        arr[i][j] = w + 1
                        ret = kotlin.math.max(ret, arr[i][j] * arr[i][j])
                    }
                }
            }
        }
        return ret
    }

    // Rolling dp with space complexity of O(n).
    // I don't think it's possible to go less since when changing line,
    // we still need the top of line begining information, so 2x2 will not do it.
    fun maximalSquare2(matrix: Array<CharArray>): Int {
        var ret = 0
        val m = matrix.size
        val n = matrix[0].size
        val arr = Array(2) { IntArray(n) }
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (matrix[i][j] == '0') {
                    arr[i % 2][j] = 0
                    continue
                }
                when {
                    i == 0 || j == 0 -> {
                        arr[i % 2][j] = 1
                        ret = kotlin.math.max(ret, 1)
                    }

                    else -> {
                        val left = arr[i % 2][j - 1]
                        val top = arr[(i - 1) % 2][j]
                        val topLeft = arr[(i - 1) % 2][j - 1]
                        val w = kotlin.math.min(kotlin.math.min(left, top), topLeft)
                        arr[i % 2][j] = w + 1
                        ret = kotlin.math.max(ret, arr[i % 2][j] * arr[i % 2][j])
                    }
                }
            }
        }
        return ret
    }
}