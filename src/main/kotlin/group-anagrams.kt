/**
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 *
 * Constraints:
 * 1 <= strs.length <= 10^4
 * 0 <= strs[i].length <= 100
 * strs[i] consists of lowercase English letters.
 */
class Solution_group_anagrams {

    // Hashing with sorted string.
    // T: O(n*klogk), n is size of strs and k is max len of string inside strs; S: O(nk)
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val ret = mutableListOf<List<String>>()
        val aMap = hashMapOf<String, MutableList<String>>()
        for (s in strs) {
            val a = String(s.toCharArray().sortedArray())
            if (aMap.containsKey(a)) {
                aMap[a]?.add(s)
            } else {
                aMap[a] = mutableListOf(s)
            }
        }
        for (v in aMap.values) {
            ret.add(v)
        }
        return ret
    }

    // Instead of hashing on sorted string, use char count string
    // T:O(n*k) S:O(n*k)
    fun groupAnagrams2(strs: Array<String>): List<List<String>> {
        val ans: MutableMap<String, MutableList<String>> = HashMap()
        val count = IntArray(26)
        for (s in strs) {
            Arrays.fill(count, 0)
            for (c in s) count[c - 'a']++
            val sb = StringBuilder()
            for (i in 0..25) {
                sb.append('#')
                sb.append(count[i])
            }
            val key = sb.toString()
            if (!ans.containsKey(key)) ans[key] = mutableListOf()
            ans[key]?.add(s)
        }
        return ArrayList(ans.values)
    }
}