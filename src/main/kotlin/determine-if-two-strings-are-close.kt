/**
 * Two strings are considered close if you can attain one from the other using the following operations:
 * Operation 1: Swap any two existing characters.
 * For example, abcde -> aecdb
 * Operation 2: Transform every occurrence of one existing character into another existing character, and do the same with the other character.
 * For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
 * You can use the operations on either string as many times as necessary.
 * Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.
 *
 * 1 <= word1.length, word2.length <= 10^5
 * word1 and word2 contain only lowercase English letters.
 */
class Solution_determine_if_two_strings_are_close {

    // We can see that the operation cannot generate new char and cannot change length
    // So first compare length, and also need to make sure char sets are the same
    // After that, since chars are able to swap with operation 2, just need to check if counts are in the same distribution
    // T:O(n) S:O(1)
    fun closeStrings(word1: String, word2: String): Boolean {
        if (word1.length != word2.length) return false

        val cnt1 = IntArray(26)
        val cnt2 = IntArray(26)
        for (i in word1.indices) {
            cnt1[word1[i] - 'a']++
            cnt2[word2[i] - 'a']++
        }

        // At most 26 counts, thus sort is still O(1)
        val l1 = mutableListOf<Int>()
        val l2 = mutableListOf<Int>()
        for (i in cnt1.indices) {
            // Char cannot match if one is 0 and one is not 0
            if (cnt1[i] * cnt2[i] == 0 && cnt1[i] + cnt2[i] != 0) return false
            if (cnt1[i] != 0) {
                l1.add(cnt1[i])
                l2.add(cnt2[i])
            }
        }
        return l1.sorted() == l2.sorted()
    }
}