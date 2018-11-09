/**
 * N couples sit in 2N seats arranged in a row and want to hold hands.
 * We want to know the minimum number of swaps so that every couple is sitting side by side.
 * A swap consists of choosing any two people, then they stand up and switch seats.
 *
 * The people and seats are represented by an integer from 0 to 2N-1, the couples are numbered in order,
 * the first couple being (0, 1), the second couple being (2, 3), and so on with the last couple being (2N-2, 2N-1).
 *
 * The couples' initial seating is given by row_i
 * being the value of the person who is initially sitting in the i-th seat.
 *
 * Example 1:
 *
 * Input: row = [0, 2, 1, 3]
 * Output: 1
 * Explanation: We only need to swap the second (row[1]) and third (row[2]) person.
 *
 * Example 2:
 *
 * Input: row = [3, 2, 0, 1]
 * Output: 0
 * Explanation: All couples are already seated side by side.
 * Note:
 *
 * 1. len(row) is even and in the range of [4, 60].
 * 2. row is guaranteed to be a permutation of 0...len(row)-1.
 */

class Solution_couples_holding_hands {

    // First observation: the 2N seats can be divided into N brackets,
    // so that no couple could break the bracket, otherwise the first and last index cannot be coupled.
    // So as long as person is in the bracket, it does not matter which exact index he/she is at.
    // Also with this concept, each person can only have 1 position for couple instead of 2.
    // For example, [99, 1, ...], when coupling for 1, we can just look at index 0 instead of also considering index 2.

    // Second Observation: this is actually a cyclic permutation problem.
    // Each bracket will point to 2 brackets, but we can easily define a direction.
    // For example, with [(3, 4), (2, 6), (3, 1), (0, 7)], we always start with left, so as in bracket:
    // 3 -> 4 which is in the same bracket, next time 4 -> 3 use right instead. And a cycle found.
    // 2 -> 3, then 1 -> 0, 7 -> 6 back to (2, 6) bracket, cycle found again.
    // This is indeed an abstract cyclic permutation [0, 2, 3, 1].
    // Therefore, this example is a 1 cycle + 3 cycle.
    // From wiki we know: If the cyclic permutation involve k elements, we need k-1 swaps.
    // So say we have m cycles c1, ..., cm, each has count of k1, ..., km,
    // namely we need sum(k1-1,k2-1,...,km-1).
    // Alternatively, required result equals to n - (# of cycles).
    // The reason is simple:
    // We have sum(k1,k2,...,km) = n, so sum(k1-1,k2-1,...,km-1) = n - 1*m = n - # of cycles.
    // So we just need to get those cyclic permutations count. The example described above will return 2.

    // Note: this problem also could be solved with union-found,
    // see https://leetcode.com/problems/couples-holding-hands/discuss/117520/Java-union-find-easy-to-understand-5-ms
    // T:O(n) S:O(n)
    fun minSwapsCouples(row: IntArray): Int {
        // bracket size
        val n = row.size / 2
        // use a map to record index
        val map = mutableMapOf<Int, Int>()
        for ((i, num) in row.withIndex()) {
            map[num] = i
        }
        // now count cyclic permutation
        var cnt = 0
        val visited = BooleanArray(n, { false })
        var i = 0
        while (i < n) {
            var useLeft = true
            val start = i
            while (!visited[i]) {
                visited[i] = true
                // trying to find cycle
                // who we are looking at
                val p = if (useLeft) row[i * 2] else row[i * 2 + 1]
                // who is couple
                val q = if (p % 2 == 0) p + 1 else p - 1
                // next time use left or right?
                useLeft = (map[q] ?: 0) % 2 == 1
                // where is couple in bracket
                val next = (map[q] ?: 0) / 2
                i = next
                if (start == i) {
                    // cycle found
                    cnt++
                }
            }
            i++
        }
        return n - cnt
    }
}

fun main(args: Array<String>) {

}