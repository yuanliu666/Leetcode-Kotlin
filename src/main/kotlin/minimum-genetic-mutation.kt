/**
 * A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.
 * Suppose we need to investigate a mutation from a gene string startGene to a gene string endGene
 * where one mutation is defined as one single character changed in the gene string.
 * For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
 * There is also a gene bank bank that records all the valid gene mutations.
 * A gene must be in bank to make it a valid gene string.
 * Given the two gene strings startGene and endGene and the gene bank bank,
 * return the minimum number of mutations needed to mutate from startGene to endGene.
 * If there is no such a mutation, return -1.
 * Note that the starting point is assumed to be valid, so it might not be included in the bank.
 */
class Solution_minimum_genetic_mutation {

    // BFS. T:O(B*L), B is # of strings in bank, L is length of gene; S:O(B*L)
    // Since we at most examine all strings in bank
    fun minMutation(startGene: String, endGene: String, bank: Array<String>): Int {
        val set = hashSetOf<String>()
        val q = LinkedList<String>()
        q.add(startGene)
        var d = 0

        while (q.isNotEmpty()) {
            val next = LinkedList<String>()
            for (s in q) {
                if (s == endGene) {
                    return d
                }
                set.add(s)
                for (t in bank) {
                    if (!set.contains(t) && isOneMutationAway(s, t)) {
                        next.add(t)
                        set.add(t)
                    }
                }
            }
            d++
            q.clear()
            q.addAll(next)
        }
        return -1
    }

    // T:O(n) S:O(1)
    private fun isOneMutationAway(s: String, t: String): Boolean {
        var edit = false
        for (i in s.indices) {
            if (s[i] != t[i]) {
                if (edit) return false
                edit = true
            }
        }
        // need exactly 1 diff, no diff is also false
        return edit
    }
}