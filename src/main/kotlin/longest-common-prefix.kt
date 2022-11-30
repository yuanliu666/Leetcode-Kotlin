/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 */
class Solution_longest_common_prefix {

    // T:O(n*L), n is number of strings, L is minimum length of strings, S:O(L)
    fun longestCommonPrefix(strs: Array<String>): String {
        val sb = StringBuilder()
        for (i in strs[0].indices) {
            val c: Char = strs[0][i]
            for (s in strs) {
                if (i >= s.length || s[i] != c) {
                    return sb.toString()
                }
            }
            sb.append(c)
        }
        return sb.toString()
    }
}