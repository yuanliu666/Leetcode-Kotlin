/**
 * Given a string S, find the number of different non-empty palindromic subsequences in S,
 * and return that number modulo 10^9 + 7.
 *
 * A subsequence of a string S is obtained by deleting 0 or more characters from S.
 *
 * A sequence is palindromic if it is equal to the sequence reversed.
 *
 * Two sequences A_1, A_2, ... and B_1, B_2, ... are different if there is some i for which A_i != B_i.
 *
 * Example 1:
 * Input:
 * S = 'bccb'
 * Output: 6
 * Explanation:
 * The 6 different non-empty palindromic subsequences are 'b', 'c', 'bb', 'cc', 'bcb', 'bccb'.
 * Note that 'bcb' is counted only once, even though it occurs twice.
 *
 * Example 2:
 * Input:
 * S = 'abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba'
 * Output: 104860361
 * Explanation:
 * There are 3104860382 different non-empty palindromic subsequences, which is 104860361 modulo 10^9 + 7.
 * Note:
 *
 * The length of S will be in the range [1, 1000].
 * Each character S\[i] will be in the set {'a', 'b', 'c', 'd'}.
 */

class Solution_count_different_palindromic_subsequences {

    // T:O(n^2) S:O(n^2)
    fun countPalindromicSubsequences(s: String): Int {
        val dp = Array(s.length) { IntArray(s.length) }
        for (i in 0 until s.length) {
            // init dp values for single char
            dp[i][i] = 1
        }

        for (distance in 1 until s.length) {
            for (i in 0 until s.length - distance) {
                val j = i + distance
                if (s[i] == s[j]) {
                    var low = i + 1
                    var high = j - 1

                    /* Variable low and high here are used to get rid of the duplicate*/

                    while (low <= high && s[low] != s[j]) {
                        low++
                    }
                    while (low <= high && s[high] != s[j]) {
                        high--
                    }
                    dp[i][j] = when {
                    // consider the string from i to j is "a...a" "a...a"... where there is no character 'a' inside the leftmost and rightmost 'a'
                    /* eg:  "aba" while i = 0 and j = 2:  dp[1][1] = 1 records the palindrome{"b"},
                     the reason why dp[i + 1][j  - 1] * 2 counted is that we count dp[i + 1][j - 1] one time as {"b"},
                     and additional time as {"aba"}. The reason why 2 counted is that we also count {"a", "aa"}.
                     So totally dp[i][j] record the palindrome: {"a", "b", "aa", "aba"}.
                     */
                        low > high -> dp[i + 1][j - 1] * 2 + 2
                    // consider the string from i to j is "a...a...a" where there is only one character 'a' inside the leftmost and rightmost 'a'
                    /* eg:  "aaa" while i = 0 and j = 2: the dp[i + 1][j - 1] records the palindrome {"a"}.
                     the reason why dp[i + 1][j  - 1] * 2 counted is that we count dp[i + 1][j - 1] one time as {"a"},
                     and additional time as {"aaa"}. the reason why 1 counted is that
                     we also count {"aa"} that the first 'a' come from index i and the second come from index j. So totally dp[i][j] records {"a", "aa", "aaa"}
                    */
                        low == high -> dp[i + 1][j - 1] * 2 + 1
                    // consider the string from i to j is "a...a...a... a" where there are at least two character 'a' close to leftmost and rightmost 'a'
                    /* eg: "aacaa" while i = 0 and j = 4: the dp[i + 1][j - 1] records the palindrome {"a",  "c", "aa", "aca"}.
                      the reason why dp[i + 1][j  - 1] * 2 counted is that we count dp[i + 1][j - 1] one time as {"a",  "c", "aa", "aca"},
                      and additional time as {"aaa",  "aca", "aaaa", "aacaa"}.  Now there is duplicate :  {"aca"},
                      which is removed by deduce dp[low + 1][high - 1]. So totally dp[i][j] record {"a",  "c", "aa", "aca", "aaa", "aaaa", "aacaa"}
                      */
                        else -> dp[i + 1][j - 1] * 2 - dp[low + 1][high - 1]
                    }
                } else {
                    dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1]  //s.charAt(i) != s.charAt(j)
                }
                // deal with possible overflow
                dp[i][j] = if (dp[i][j] < 0) dp[i][j] + 1000000007 else dp[i][j] % 1000000007
            }
        }
        return dp[0][s.length - 1]
    }
}

fun main(args: Array<String>) {
    // LC OJ passed
}