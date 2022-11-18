/**
 * Given two strings s and t, determine if they are isomorphic.
 * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
 * All occurrences of a character must be replaced with another character while preserving the order of characters.
 * No two characters may map to the same character, but a character may map to itself.
 *
 * 1 <= s.length <= 5 * 10^4
 * t.length == s.length
 * s and t consist of any valid ascii character.
 */
class Solution_isomorphic_strings {

    // T:O(n) S:O(n)
    fun isIsomorphic(s: String, t: String): Boolean {
        if (s.length != t.length) return false
        val map = hashMapOf<Char, Char>()
        val set = hashSetOf<Char>()
        for (i in s.indices) {
            val c = s[i]
            val ct = t[i]
            if (map.containsKey(c)) {
                if (ct != map[c]) {
                    return false
                }
            } else {
                if (set.contains(ct)) {
                    return false
                }
                map[c] = ct
                set.add(ct)
            }
        }
        return true
    }
}