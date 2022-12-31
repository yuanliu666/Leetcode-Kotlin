/**
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words
 * beginWord -> s1 -> s2 -> ... -> sk such that:
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence
 * from beginWord to endWord, or 0 if no such sequence exists.
 *
 * Constraints:
 * 1 <= beginWord.length <= 10
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * wordList[i].length == beginWord.length
 * beginWord, endWord, and wordList[i] consist of lowercase English letters.
 * beginWord != endWord
 * All the words in wordList are unique.
 */
class Solution_word_ladder {

    // Convert to graph and do BFS.
    // The major cost is graph construction, which is O(L*n^2).
    // S:O(L*n^2)
    fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): Int {
        val map = hashMapOf<String, MutableSet<String>>()
        // Include begin and also check if end is in list
        map[beginWord] = hashSetOf()
        var hasEndWord = false
        for (i in wordList.indices) {
            if (isAdjacent(beginWord, wordList[i])) {
                map[beginWord]?.add(wordList[i])
            }
            if (wordList[i] == endWord) hasEndWord = true
        }
        if (!hasEndWord) return 0
        // Construct graph from list
        for (i in 0 until wordList.size - 1) {
            for (j in i + 1 until wordList.size) {
                if (isAdjacent(wordList[i], wordList[j])) {
                    map.putIfAbsent(wordList[i], hashSetOf())
                    map[wordList[i]]?.add(wordList[j])
                    map.putIfAbsent(wordList[j], hashSetOf())
                    map[wordList[j]]?.add(wordList[i])
                }
            }
        }
        // BFS
        val cur = mutableListOf(beginWord)
        val visited = hashSetOf(beginWord)
        var cnt = 1
        while (cur.isNotEmpty()) {
            val list = mutableListOf<String>()
            for (w in cur) {
                if (w == endWord) return cnt
                for (next in map[w] ?: hashSetOf()) {
                    if (!visited.contains(next)) {
                        visited.add(next)
                        list.add(next)
                    }
                }
            }
            cnt++
            cur.clear()
            cur.addAll(list)
        }
        return 0
    }


    // T:O(L)
    private fun isAdjacent(a: String, b: String): Boolean {
        if (a.length != b.length) return false
        var diff = 0
        for (i in a.indices) {
            if (a[i] != b[i]) {
                diff++
                if (diff > 1) break
            }
        }
        return diff == 1
    }
}