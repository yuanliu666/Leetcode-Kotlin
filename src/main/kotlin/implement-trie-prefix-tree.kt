/**
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings.
 * There are various applications of this data structure, such as autocomplete and spellchecker.
 * Implement the Trie class:
 * Trie() Initializes the trie object.
 * void insert(String word) Inserts the string word into the trie.
 * boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
 * boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
 *
 * Constraints:
 * 1 <= word.length, prefix.length <= 2000
 * word and prefix consist only of lowercase English letters.
 * At most 3 * 10^4 calls in total will be made to insert, search, and startsWith.
 */
class Solution_implement_trie_prefix_tree {

    // Utilize the fact of only lower case English letters
    class TrieNode(c: Char) {
        var isWord = false
        val children: Array<TrieNode?> = Array(26) { null }
    }

    private var root: TrieNode = TrieNode('#')

    // T:O(n) S:O(n)
    fun insert(word: String) {
        var cur = root
        for (c in word) {
            val idx = c - 'a'
            if (cur.children[idx] == null) {
                cur.children[idx] = TrieNode(c)
            }
            cur = cur.children[idx]!!
        }
        cur.isWord = true
    }

    // T:O(n)
    fun search(word: String): Boolean {
        var cur = root
        for (c in word) {
            val idx = c - 'a'
            if (cur.children[idx] == null) return false
            cur = cur.children[idx]!!
        }
        return cur.isWord
    }

    // T:O(n)
    fun startsWith(prefix: String): Boolean {
        var cur = root
        for (c in prefix) {
            val idx = c - 'a'
            if (cur.children[idx] == null) return false
            cur = cur.children[idx]!!
        }
        return true
    }
}