import java.util.PriorityQueue

/**
 * Given an array of strings words and an integer k, return the k most frequent strings.
 * Return the answer sorted by the frequency from highest to lowest.
 * Sort the words with the same frequency by their lexicographical order.
 */
class Solution_top_k_frequent_words {
    fun topKFrequent(words: Array<String>, k: Int): List<String> {
        val map = hashMapOf<String, Int>()
        words.forEach {
            map.putIfAbsent(it, 0)
            map[it] = map[it]!! + 1
        }
        // Must be a min heap since we need to kick min value once reached size of k+1
        // So make it reverse order to the actual result
        val heap = PriorityQueue(k, compareBy<Map.Entry<String, Int>> { it.value }.thenByDescending { it.key })
        for (e in map.entries) {
            heap.offer(e)
            if (heap.size == k + 1) {
                heap.poll()
            }
        }
        val ret = mutableListOf<String>()
        while (heap.isNotEmpty()) {
            ret.add(heap.poll().key)
        }
        return ret.reversed()
    }
}