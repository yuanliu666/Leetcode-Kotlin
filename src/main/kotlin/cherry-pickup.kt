/**
 * In a N x N grid representing a field of cherries, each cell is one of three possible integers.
 *
 * 0 means the cell is empty, so you can pass through;
 * 1 means the cell contains a cherry, that you can pick up and pass through;
 * -1 means the cell contains a thorn that blocks your way.
 * Your task is to collect maximum number of cherries possible by following the rules below:
 *
 * Starting at the position (0, 0) and reaching (N-1, N-1) by moving right or down through valid path cells
 * (cells with value 0 or 1);
 * After reaching (N-1, N-1), returning to (0, 0) by moving left or up through valid path cells;
 * When passing through a path cell containing a cherry, you pick it up and the cell becomes an empty cell (0);
 * If there is no valid path between (0, 0) and (N-1, N-1), then no cherries can be collected.
 *
 * Example 1:
 * Input: grid =
 * [[0, 1, -1],
 * [1, 0, -1],
 * [1, 1,  1]]
 * Output: 5
 * Explanation:
 * The player started at (0, 0) and went down, down, right right to reach (2, 2).
 * 4 cherries were picked up during this single trip, and the matrix becomes [[0,1,-1],[0,0,-1],[0,0,0]].
 * Then, the player went left, up, up, left to return home, picking up one more cherry.
 * The total number of cherries picked up is 5, and this is the maximum possible.
 *
 * Note:
 *
 * 1. grid is an N by N 2D array, with 1 <= N <= 50.
 * 2. Each grid\[i]\[j] is an integer in the set {-1, 0, 1}.
 * 3. It is guaranteed that grid[0][0] and grid[N-1][N-1] are not -1.
 */

class Solution_cherry_pickup {

    // https://leetcode.com/problems/cherry-pickup/discuss/109903/Step-by-step-guidance-of-the-O(N3)-time-and-O(N2)-space-solution
    // T:O(N^3) S:O(N^2)
    fun cherryPickup(grid: Array<IntArray>): Int {
        val N = grid.size
        val maxLen = (N shl 1) - 1
        val dp = Array(N, { IntArray(N) })
        dp[0][0] = grid[0][0]
        for (n in 1 until maxLen) {
            for (i in N - 1 downTo 0) {
                for (p in N - 1 downTo 0) {
                    val j = n - i
                    val q = n - p
                    if (j < 0 || j >= N || q < 0 || q >= N || grid[i][j] < 0 || grid[p][q] < 0) {
                        dp[i][p] = -1
                        continue
                    }
                    if (i > 0) dp[i][p] = Math.max(dp[i][p], dp[i - 1][p])
                    if (p > 0) dp[i][p] = Math.max(dp[i][p], dp[i][p - 1])
                    if (i > 0 && p > 0) dp[i][p] = Math.max(dp[i][p], dp[i - 1][p - 1])
                    if (dp[i][p] >= 0) dp[i][p] += grid[i][j] + (if (i != p) grid[p][q] else 0)
                }
            }
        }
        return Math.max(dp[N - 1][N - 1], 0)
    }
}

fun main(args: Array<String>) {
    val s = Solution_cherry_pickup()
    println(s.cherryPickup(arrayOf(
            intArrayOf(0, 1, -1),
            intArrayOf(1, 0, -1),
            intArrayOf(1, 1, 1)
    )))
}