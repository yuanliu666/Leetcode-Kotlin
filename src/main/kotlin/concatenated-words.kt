/**
 * Given a list of words (without duplicates),
 * please write a program that returns all concatenated words in the given list of words.
 * A concatenated word is defined as a string that is comprised entirely of
 * at least two shorter words in the given array.
 *
 * Example:
 * Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 *
 * Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 *
 * Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 * "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
 * "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
 * Note:
 * 1. The number of elements of the given array will not exceed 10,000
 * 2. The length sum of elements in the given array will not exceed 600,000.
 * 3. All the input string will only include lower case letters.
 * 4. The returned elements order does not matter.
 */

class Solution_concatenated_wors {

    // original solution, slow
    // T:O(nlogn + n^3)
    // sort by length, the key is how to judge concatenate
    fun findAllConcatenatedWordsInADict(words: Array<String>): List<String> {
        words.sortBy { it.length }
        val ret = mutableListOf<String>()
        val baseWords = mutableListOf<String>()
        for (w in words) {
            if (canConcatenate(baseWords, w)) {
                // deal with input=[""] case
                if (w.isNotEmpty()) {
                    ret.add(w)
                }
            } else {
                baseWords.add(w)
            }
        }
        return ret
    }

    private fun canConcatenate(baseWords: List<String>, candidate: String): Boolean {
        var flag = false
        when {
            candidate.isEmpty() -> return true
            baseWords.isEmpty() -> return false
            else -> {
                for (w in baseWords) {
                    if (candidate.startsWith(w)) {
                        flag = flag || canConcatenate(baseWords, candidate.substring(w.length))
                        if (flag) {
                            return true
                        }
                    }
                }
            }
        }
        return flag
    }

    // dp based solution, much faster
    // T:  O(n * L^2)
    // S: O(n * L) where n is size of words array, L is average length of word
    fun findAllConcatenatedWordsInADict2(words: Array<String>): List<String> {
        val lookup: MutableSet<String> = mutableSetOf()
        lookup.addAll(words)
        val ret = mutableListOf<String>()
        for (w in words) {
            val dp = Array(w.length + 1, { false })
            dp[0] = true
            for (i in 0 until w.length) {
                if (!dp[i]) {
                    continue
                }
                for (j in (i + 1) until (w.length + 1)) {
                    if (j - i < w.length && lookup.contains(w.substring(i, j))) {
                        dp[j] = true
                    }
                }
                if (dp[w.length]) {
                    ret.add(w)
                    break
                }
            }
        }
        return ret
    }
}

fun main(args: Array<String>) {
    // LC OJ Passed
    val s = Solution_concatenated_wors()
    println(s.findAllConcatenatedWordsInADict(arrayOf("cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat")))
    println(s.findAllConcatenatedWordsInADict(arrayOf("")))
    println(s.findAllConcatenatedWordsInADict2(arrayOf("cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat")))
    println(s.findAllConcatenatedWordsInADict2(arrayOf("")))
}