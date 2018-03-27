/**
 * There is a new alien language which uses the latin alphabet.
 *
 * However, the order among letters are unknown to you.
 *
 * You receive a list of words from the dictionary,
 *
 * where words are sorted lexicographically by the rules of this new language.
 *
 * Derive the order of letters in this language.
 *
 * For example,
 *
 * Given the following words in dictionary,
 *
 * [
 * "wrt",
 * "wrf",
 * "er",
 * "ett",
 * "rftt"
 * ]
 *
 * The correct order is: "wertf".
 *
 * Note:
 *
 * You may assume all letters are in lowercase.
 *
 * If the order is invalid, return an empty string.
 *
 * There may be multiple valid order of letters, return any one of them is fine.
 */

import utils.MyDeque

class Solution_alien_dictionary {

    // topological sort or return empty if cycle detected
    // T:O(n) S:O(|V|+|E|)=O(26+26^2)=O(1)

    // BFS
    fun getAlienDictionaryOrderBFS(words: List<String>): String {
        val ret = StringBuilder()

        // we see a char as a node
        val zeroInDegreeDq = MyDeque<Char>()
        // key is entered from values
        val inDegree = hashMapOf<Char, HashSet<Char>>()
        // key enters to values
        val outDegree = hashMapOf<Char, HashSet<Char>>()
        val nodes = hashSetOf<Char>()

        // get all chars
        words.forEach({ it.forEach { nodes.add(it) } })

        // build graph
        for (i in 1..words.size - 1) {
            if (words[i - 1].length > words[i].length
                    && words[i - 1].substring(0..words[i].length - 1) == words[i])
                return ""
            findEdgesBFS(words[i - 1], words[i], inDegree, outDegree)
        }

        // get 0 in degree nodes to start with
        nodes.filter { it !in inDegree }.forEach({ zeroInDegreeDq.addFirst(it) })

        var precedence: Char
        while (zeroInDegreeDq.peekFirst() != null) {
            precedence = zeroInDegreeDq.getLast()!!
            ret.append(precedence)

            if (precedence in outDegree) {
                for (c in outDegree[precedence]!!) {
                    inDegree[c]?.remove(precedence)
                    if (inDegree[c]!!.isEmpty())
                        zeroInDegreeDq.addFirst(c)
                }
                outDegree.remove(precedence)
            }
        }
        if (outDegree.isNotEmpty())
            return ""
        return ret.toString()
    }

    fun findEdgesBFS(word1: String, word2: String, inDegree: HashMap<Char, HashSet<Char>>, outDegree: HashMap<Char, HashSet<Char>>) {
        val len = minOf(word1.length, word2.length)
        for (i in 0..len - 1) {
            if (word1[i] != word2[i]) {
                if (word2[i] !in inDegree) {
                    inDegree.put(word2[i], hashSetOf())
                }
                if (word1[i] !in outDegree) {
                    outDegree.put(word1[i], hashSetOf())
                }
                inDegree[word2[i]]?.add(word1[i])
                outDegree[word1[i]]?.add(word2[i])
                break
            }
        }
    }

    // DFS
    fun getAlienDictionaryOrderDFS(words: List<String>): String {
        val nodes = hashSetOf<Char>()
        val ancestors = hashMapOf<Char, ArrayList<Char>>()
        words.forEach { it.forEach { nodes.add(it) } }
        for (node in nodes)
            ancestors.put(node, arrayListOf())
        for (i in 1..words.size - 1) {
            if (words[i - 1].length > words[i].length && words[i - 1].substring(0..words[i].length - 1) == words[i])
                return ""
            findEdgesDFS(words[i - 1], words[i], ancestors)
        }
        val sb = StringBuilder()
        val visited = hashMapOf<Char, Char>()
        if (nodes.any { topSortDFS(it, it, ancestors, visited, sb) })
            return ""

        return sb.toString()
    }

    fun findEdgesDFS(word1: String, word2: String, ancestors: HashMap<Char, ArrayList<Char>>) {
        val minLen = minOf(word1.length, word2.length)
        for (i in 0..minLen - 1)
            if (word1[i] != word2[i]) {
                ancestors[word2[i]]?.add(word1[i])
                break
            }
    }

    fun topSortDFS(root: Char, node: Char, ancestors: HashMap<Char, ArrayList<Char>>, visited: HashMap<Char, Char>, sb: StringBuilder): Boolean {
        if (node !in visited) {
            visited.put(node, root)
            ancestors[node]?.any { topSortDFS(root, it, ancestors, visited, sb) }?.let {
                if (it)
                    return it
            }
            sb.append(node)
        } else if (visited[node] == root) {
            return true
        }
        return false
    }
}

fun main(args: Array<String>) {
    val s = Solution_alien_dictionary()
    println(s.getAlienDictionaryOrderDFS(arrayListOf(
            "wrt",
            "wrf",
            "er",
            "ett",
            "rftt"
    )))
}
