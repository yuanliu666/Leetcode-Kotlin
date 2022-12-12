/**
 * Given a string s, find the length of the longest substring without repeating characters.
 */
class Solution_longest_substring_without_repeating_characters {

    // Sliding window, T:O(n) S:O(n)
    fun lengthOfLongestSubstring(s: String): Int {
        if (s.isEmpty()) return 0
        val map = hashMapOf(s[0] to 1)
        var ret = 1
        var curLen = 1
        var start = 0
        // start and e are inclusive
        for (e in 1 until s.length) {
            if (!map.containsKey(s[e]) || map[s[e]] == 0) {
                // If new char, put in and continue
                map[s[e]] = 1
                curLen++
                ret = kotlin.math.max(ret, curLen)
            } else {
                map[s[e]] = map[s[e]]!! + 1
                // Otherwise, we need to shrink start until no repeat
                while (map[s[e]]!! > 1) {
                    map[s[start]] = map[s[start]]!! - 1
                    start++
                }
                curLen = e - start + 1
            }
        }
        return ret
    }
}