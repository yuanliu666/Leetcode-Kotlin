/**
 * Given a string s and a dictionary of strings wordDict,
 * return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 */
class Solution_word_break {

    // DP solution. dp[i] means s[i] can be break into words or not.
    // T:O(L*n*m), where L is length of s, n is number of words in wordDict, and m is length of words in wordDict.
    // S:O(L)
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        val dp = BooleanArray(s.length + 1)
        dp[0] = true
        for (i in 1 until dp.size) {
            for (w in wordDict) {
                val last = i - w.length
                // If no enough length, or previous spot is false, or not the same string, keep it false
                // Note that in s, indexes are smaller by 1
                if (last < 0 || !dp[last] || w != s.substring(last until i)) continue
                dp[i] = true
                break
            }
        }
        return dp[dp.size - 1]
    }
}