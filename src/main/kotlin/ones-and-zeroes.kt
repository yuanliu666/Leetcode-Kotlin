/**
 * You are given an array of binary strings strs and two integers m and n.
 * Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.
 * A set x is a subset of a set y if all elements of x are also elements of y.
 */
class Solution_ones_and_zeroes {

    // dp[i][j][k] means the maximum number of strings we can get from the first i argument strs
    // using limited j number of '0's and k number of '1's.
    // For dp[i][j][k], we can get it by fetching the current string i or discarding the current string,
    // which would result in dp[i][j][k] = max(dp[i-1][j-numOfZero(strs[i])][i-numOfOnes(strs[i])], dp[i-1][j][k])
    // T:O(L*m*n) S:O(L*m*n)
    // For potential optimiaztion, since we only cares about i - 1, this dimension can be compressed,
    // which will give better space complexity of S(m*n)
    fun findMaxForm(strs: Array<String>, m: Int, n: Int): Int {
        val len = strs.size
        val dp = Array(len + 1) {
            Array(m + 1) {
                IntArray(
                    n + 1
                )
            }
        }
        for (i in 0..len) {
            var nums = intArrayOf()
            if (i > 0) {
                nums = calculate(strs[i - 1])
            }
            for (j in 0..m) {
                for (k in 0..n) {
                    if (i == 0) {
                        dp[i][j][k] = 0
                    } else if (j >= nums[0] && k >= nums[1]) {
                        dp[i][j][k] = kotlin.math.max(dp[i - 1][j][k], dp[i - 1][j - nums[0]][k - nums[1]] + 1)
                    } else {
                        dp[i][j][k] = dp[i - 1][j][k]
                    }
                }
            }
        }
        return dp[len][m][n]
    }

    private fun calculate(str: String): IntArray {
        val zeros = str.count { it == '0' }
        return intArrayOf(zeros, str.length - zeros)
    }
}