/**
 * Given a string s which consists of lowercase or uppercase letters,
 * return the length of the longest palindrome that can be built with those letters.
 * Letters are case-sensitive, for example, "Aa" is not considered a palindrome here.
 *
 * 1 <= s.length <= 2000
 * s consists of lowercase and/or uppercase English letters only.
 */
class Solution_longest_palindrome {

    // T:O(n) S:O(1)
    fun longestPalindrome(s: String): Int {
        val upper = IntArray(26)
        val lower = IntArray(26)
        for (c in s) {
            if (c.isUpperCase()) {
                upper[c - 'A']++
            } else {
                lower[c - 'a']++
            }
        }
        var hasExtra = false
        var ret = 0
        for (i in upper.indices) {
            ret += 2 * (upper[i] / 2) + 2 * (lower[i] / 2)
            if (!hasExtra && (upper[i] % 2 == 1 || lower[i] % 2 == 1)) {
                hasExtra = true
            }
        }
        if (hasExtra) {
            ret++
        }
        return ret
    }
}