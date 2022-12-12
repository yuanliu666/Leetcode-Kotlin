/**
 * Given two strings s and t of lengths m and n respectively, return the minimum window substring of s
 * such that every character in t (including duplicates) is included in the window.
 * If there is no such substring, return the empty string "".
 * The testcases will be generated such that the answer is unique.
 *
 *  m == s.length
 *  n == t.length
 *  1 <= m, n <= 10^5
 *  s and t consist of uppercase and lowercase English letters.
 */
class Solution_minimum_window_substring {

    // T:O(m+n) S:O(1)
    fun minWindow(s: String, t: String): String {
        if (s.length < t.length) return ""
        val tMap = hashMapOf<Char, Int>()
        for (c in t) {
            tMap.putIfAbsent(c, 0)
            tMap[c] = tMap[c]!! + 1
        }
        val sMap = hashMapOf<Char, Int>()
        var minLen = Int.MAX_VALUE                            // Use to record result
        var endIdx = -1

        var start = 0
        for (e in s.indices) {
            if (!tMap.containsKey(s[e])) continue             // Irrelevant char
            sMap.putIfAbsent(s[e], 0)
            sMap[s[e]] = sMap[s[e]]!! + 1
            if (!containsMap(sMap, tMap)) continue            // Window not found yet
            // Try shrink start while keeping window within requirement
            while (start <= e) {
                if (!tMap.containsKey(s[start])) {
                    start++
                } else {
                    if (sMap[s[start]]!! > tMap[s[start]]!!) {
                        sMap[s[start]] = sMap[s[start]]!! - 1
                        start++
                    } else {
                        break
                    }
                }
            }
            val curLen = e - start + 1
            if (curLen < minLen) {
                minLen = curLen
                endIdx = e
            }
        }
        return if (endIdx == -1) "" else s.substring(endIdx + 1 - minLen..endIdx)
    }

    // This is in theory T:O(1) since string only has lower and upper chars
    private fun containsMap(m1: Map<Char, Int>, m2: Map<Char, Int>): Boolean {
        for (k in m2.keys) {
            if (!m1.containsKey(k)) return false
            if (m1[k]!! < m2[k]!!) return false
        }
        return true
    }
}