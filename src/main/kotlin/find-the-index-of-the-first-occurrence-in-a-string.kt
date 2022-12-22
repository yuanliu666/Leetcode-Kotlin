/**
 * Given two strings needle and haystack, return the index of the first occurrence of needle in haystack,
 * or -1 if needle is not part of haystack.
 *
 * Constraints:
 * 1 <= haystack.length, needle.length <= 10^4
 * haystack and needle consist of only lowercase English characters.
 */
class Solution_find_the_index_of_the_first_occurrence_in_a_string {

    // Sliding window solution. Before comparing string, compare count of window.
    // It won't affect worst case but it runs faster.
    // T:O(n*m) S:O(1)
    fun strStr(haystack: String, needle: String): Int {
        if (needle.length > haystack.length) return -1
        if (needle.length == haystack.length) return if (needle == haystack) 0 else -1
        val cnt = IntArray(26)
        for (c in needle) {
            cnt[c - 'a']++
        }

        val len = needle.length
        val cur = IntArray(26)
        for (e in haystack.indices) {
            cur[haystack[e] - 'a']++
            val start = e - len + 1
            if (start - 1 >= 0) {
                cur[haystack[start - 1] - 'a']--
            }
            if (start >= 0 && cur.contentEquals(cnt)) {
                // check if window == needle
                var match = true
                for (i in needle.indices) {
                    val idx = start + i
                    if (haystack[idx] != needle[i]) {
                        match = false
                        break
                    }
                }
                if (match) {
                    return e - len + 1
                }
            }
        }
        return -1
    }
}