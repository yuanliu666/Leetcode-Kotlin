/**
 * There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]).
 * The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
 * The robot can only move either down or right at any point in time.
 * Given the two integers m and n, return the number of possible unique paths
 * that the robot can take to reach the bottom-right corner.
 * The test cases are generated so that the answer will be less than or equal to 2 * 10^9.
 */
class Solution_unique_paths {
    
    // T:O(m*n) S:O(n)
    // Using rolling dp with 2 rows to save space as dp value only requires left and top
    fun uniquePaths(m: Int, n: Int): Int {
        val dp = Array(2) { IntArray(n) }
        for (i in 0 until m) {
            val row = i % 2
            for (j in 0 until n) {
                if (i == 0 || j == 0) {
                    dp[row][j] = 1
                } else {
                    dp[row][j] = dp[(row + 1) % 2][j] + dp[row][j - 1]
                }
            }
        }
        return dp[(m - 1) % 2][n - 1]
    }
}