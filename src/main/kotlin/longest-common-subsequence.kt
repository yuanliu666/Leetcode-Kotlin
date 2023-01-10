/**
 * Given two strings text1 and text2, return the length of their longest common subsequence.
 * If there is no common subsequence, return 0.
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted
 * without changing the relative order of the remaining characters.
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 */
class Solution_longest_common_subsequence {

    // DP solution. Let dp[i][j] means max common subsequences from t1[i] and t2[j] to their end.
    // Need to calculate from right to left, starting with small substring.
    // If same char, then can add 1 from previous result, dp[i][j] = dp[i + 1][j + 1] + 1;
    // otherwise, it is not better:  dp[i][j] = kotlin.math.max(dp[i + 1][j], dp[i][j + 1]).
    // Since only access right and bottom, this can be changed to rolling dp to save space,
    // also can use min of m and n as the array size, so the space complexity can be reduced to O(min(m, n)).
    // As it is, T:O(m*n), S:O(m*n)
    fun longestCommonSubsequence(text1: String, text2: String): Int {
        val n = text1.length
        val m = text2.length
        val dp = Array(n + 1) { IntArray(m + 1) }
        for (j in (0 until m).reversed()) {
            for (i in (0 until n).reversed()) {
                if (text1[i] == text2[j]) {
                    dp[i][j] = dp[i + 1][j + 1] + 1
                } else {
                    dp[i][j] = kotlin.math.max(dp[i + 1][j], dp[i][j + 1])
                }
            }
        }
        return dp[0][0]
    }
}