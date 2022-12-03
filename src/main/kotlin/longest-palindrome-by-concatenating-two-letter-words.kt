/**
 * You are given an array of strings words. Each element of words consists of two lowercase English letters.
 * Create the longest possible palindrome by selecting some elements from words and concatenating them in any order.
 * Each element can be selected at most once.
 * Return the length of the longest palindrome that you can create.
 * If it is impossible to create any palindrome, return 0.
 * A palindrome is a string that reads the same forward and backward.
 */
class Solution_longest_palindrome_by_concatenating_two_letter_words {

    // T:O(n) S:O(n)
    fun longestPalindrome(words: Array<String>): Int {
        val map = hashMapOf<String, Int>()

        for (w in words) {
            map.putIfAbsent(w, 0)
            map[w] = map[w]!! + 1
        }
        var ret = 0
        var hasMid = false
        for ((k, v) in map.entries) {
            if (v <= 0) continue
            val p = k.reversed()
            // For case of "aa", palindrome is self, remaining one can be used as mid, e.g. "aacbaabcaa"
            if (p == k) {
                ret += 4 * (v / 2)
                if (v % 2 == 1) hasMid = true
                // Otherwise, trying to find its palindrome
            } else if (map.containsKey(p)) {
                val cnt = kotlin.math.min(v, map[p]!!)
                ret += 4 * cnt
                // Mark p as visited, faster than commented code
                map[p] = 0
//                map[k] = map[k]!! - cnt
//                map[p] = map[p]!! - cnt
            }
        }
        if (hasMid) ret += 2
        return ret
    }
}