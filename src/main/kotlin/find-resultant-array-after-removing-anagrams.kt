/**
 * You are given a 0-indexed string array words, where words[i] consists of lowercase English letters.
 * In one operation, select any index i such that 0 < i < words.length and words[i - 1] and words[i] are anagrams,
 * and delete words[i] from words. Keep performing this operation as long as you can select an index that satisfies the conditions.
 * Return words after performing all operations.
 * It can be shown that selecting the indices for each operation in any arbitrary order will lead to the same result.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase using all the original letters exactly once.
 * For example, "dacb" is an anagram of "abdc".
*/
class Solution_find_resultant_array_after_removing_anagrams {

    // T:O(n) S:O(1)
    fun removeAnagrams(words: Array<String>): List<String> {
        val ret = mutableListOf<String>()
        var p = 0
        while (p < words.size) {
            var q = p + 1
            while (q < words.size && isAnagram(words[p], words[q])) {
                q += 1
            }
            ret.add(words[p])
            p = q
        }
        return ret
    }

    private fun isAnagram(a: String, b: String): Boolean {
        if (a.length != b.length) return false
        val arr = IntArray(26)
        for (c in a) {
            arr[c - 'a'] += 1
        }
        for (c in b) {
            arr[c - 'a'] -= 1
        }
        for (n in arr) {
            if (n != 0) return false
        }
        return true
    }
}