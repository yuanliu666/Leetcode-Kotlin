/**
 * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
 * A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of
 * the characters without disturbing the relative positions of the remaining characters.
 * (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
 *
 * 0 <= s.length <= 100
 * 0 <= t.length <= 10^4
 * s and t consist only of lowercase English letters.
 *
 * Follow up: Suppose there are lots of incoming s, say s1, s2, ..., sk where k >= 10^9,
 * and you want to check one by one to see if t has its subsequence. In this scenario, how would you change your code?
 */

class Solution_is_subsequence {

    // T:O(n1+n2) S:O(1)
    fun isSubsequence(s: String, t: String): Boolean {
        var si = 0
        var ti = 0
        while (si < s.length && ti < t.length) {
            while (ti < t.length && t[ti] != s[si]) {
                ti += 1
            }
            if (ti >= t.length) return false
            si += 1
            ti += 1
        }
        return si >= s.length
    }

    fun isSubsequence2(s: String, t: String): Boolean {
        return helper(0, 0, s, t)
    }

    private fun helper(si: Int, ti: Int, s: String, t: String): Boolean {
        if (si >= s.length) return true
        if (ti >= t.length) return false

        for (i in ti until t.length) {
            if (t[i] == s[si] && helper(si + 1, i + 1, s, t)) {
                return true
            }
        }
        return false
    }

    // For follow-up question:
    // we can store t's char with indexes list, so for each incoming word, we get the first index of remaining char
    // that is larger than previous index. This sub question can be solved with binary search.
    // T:O(len(t)) for preparation, O(len(s)*lg(len(t))) for processing s; S:O(len(t))
    fun isSubsequenceFollowUp(s: String, t: String): Boolean {
        // Map of char - indexes list
        val map = hashMapOf<Char, MutableList<Int>>()
        for (i in t.indices) {
            val c = t[i]
            map.putIfAbsent(c, mutableListOf())
            map[c]?.add(i)
        }
        // Current index in t
        var index = -1
        for (ch in s) {
            // Does not contain char
            if (!map.containsKey(ch)) {
                return false
            }
            val indexList = map[ch]!!
            // All chars are before index
            if (index >= indexList[indexList.size - 1]) {
                return false
            }
            val minIdx = getMinIndexLargerThanN(indexList, index)
            index = indexList[minIdx]
        }
        return true
    }

    private fun getMinIndexLargerThanN(list: List<Int>, n: Int): Int {
        var left = 0
        var right = list.size - 1
        while (left < right) {
            val mid = (right - left) / 2 + left
            if (list[mid] > n) {
                right = mid
            } else {
                left = mid + 1
            }
        }
        return left
    }
}