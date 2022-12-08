/**
 * We define the string base to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz", so base will look like this:
 * "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
 * Given a string s, return the number of unique non-empty substrings of s are present in base.
 */
class Solution_unique_substrings_in_wraparound_string {

    // When scan from left to right, it's simple to get max continuous length, the problem is how to get number of substrings.
    // One idea is to use L(1+L)/2, but it will fail when L gets long:
    // say string is "a..za..z", for substring length of 2, "ab" will be calculated twice.
    // So instead, record max continuous length at end for each char, and the substrings will be unique.
    // e.g. "abcd" for ending with 'd', it will contribute 4 substrings "d", "cd", "bcd", "abcd",
    // namely, number of unique substrings will be its length as each one with a different length,
    // and adding up for all other chars will give us the result.
    // T:O(n) S:O(1)
    fun findSubstringInWraproundString(p: String): Int {
        val cnt = IntArray(26)
        var maxLen = 0
        for (i in p.indices) {
            if (i > 0 && (p[i - 1] - 'a' + 1) % 26 == p[i] - 'a') {
                maxLen++
            } else {
                maxLen = 1
            }
            cnt[p[i] - 'a'] = kotlin.math.max(maxLen, cnt[p[i] - 'a'])
        }
        return cnt.sum()
    }
}