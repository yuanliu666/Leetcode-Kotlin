/**
 * Given a string s, sort it in decreasing order based on the frequency of the characters.
 * he frequency of a character is the number of times it appears in the string.
 * Return the sorted string. If there are multiple answers, return any of them.
 *
 * 1 <= s.length <= 5 * 10^5
 * s consists of uppercase and lowercase English letters and digits.
 */
class Solution_sort_characters_by_frequency {

    // T:O(n) S:O(n)
    fun frequencySort(s: String): String {
        val map = hashMapOf<Char, Int>()
        for (c in s) {
            map.putIfAbsent(c, 0)
            map[c] = map[c]!! + 1
        }
        val sb = StringBuilder(s.length)
        // Because map can only have at most 26+26 entries, sort is still O(1)
        for ((k, v) in map.entries.sortedWith(compareBy { -it.value })) {
            sb.append(k.toString().repeat(v))
        }
        return sb.toString()
    }
}