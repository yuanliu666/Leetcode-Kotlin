/**
 * Given an array of strings, return all groups of strings that are anagrams.
 *
 * Note: All inputs will be in lower-case.
 */

class Solution_anagrams {
    // T:O(n * glogg), g is the max size of groups
    // S:O(n)
    fun groupAnagrams(a: List<String>): List<List<String>> {
        return a.groupBy { it.toCharArray().sorted() }.values.toList()
    }
}

fun main(args: Array<String>) {
    val s = Solution_anagrams()
    println(s.groupAnagrams(arrayListOf("ana", "aan", "an", "a", "na")))
}
