/**
 * Given a string s, return the longest palindromic substring in s.
 */
class Solution_longest_palindromic_substring {

    // Kind of DP solution. dp[i] means longest palindrome string ending at index i.
    // So we can check the char before dp[i-1], if it equals s[i] then we can add in and extend dp[i-1] by 2.
    // If not, it's a bit tricky. Could be case like a bb b, d ababa b, so we need to check.
    // In the helper function I try to look for longest palindrome string ending at end e,
    // we know the start since going beyond that won't pass.
    // I am also using a map to avoid repeat sub problems.
    // T:O(n^2) S:O(n^2)
    fun longestPalindrome(s: String): String {
        val dp = IntArray(s.length)
        var max = 1
        var ret = s.slice(0..0)
        dp[0] = 1
        val map = hashMapOf<IntArray, Boolean>()
        for (i in 1 until s.length) {
            val len = dp[i - 1]
            val j = i - len - 1
            if (j >= 0 && s[i] == s[j]) {
                dp[i] = len + 2
            } else {
                dp[i] = helper(s, j + 1, i, map)
            }
            if (dp[i] > max) {
                max = dp[i]
                ret = s.slice(i - dp[i] + 1..i)
            }
        }
        return ret
    }

    private fun helper(s: String, start: Int, e: Int, map: HashMap<IntArray, Boolean>): Int {
        for (i in start until e) {
            if (s[i] == s[e]) {
                if (map.containsKey(intArrayOf(i, e))) {
                    if (map[intArrayOf(i, e)] == true) {
                        return e - i + 1
                    }
                } else {
                    val b = isPalindrome(s, i, e)
                    map[intArrayOf(i, e)] = b
                    if (b) return e - i + 1
                }
            }
        }
        return 1
    }

    private fun isPalindrome(s: String, start: Int, e: Int): Boolean {
        var left = start
        var right = e
        while (left < right) {
            if (s[left] != s[right]) return false
            left++
            right--
        }
        return true
    }
}