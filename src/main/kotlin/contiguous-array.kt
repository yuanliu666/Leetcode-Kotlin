/**
 * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
 *
 * Example 1:
 * Input: [0,1]
 * Output: 2
 * Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
 *
 * Example 2:
 * Input: [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 *
 * Note: The length of the given binary array will not exceed 50,000.
 */

class Solution_contiguous_array {

    // T:O(n) S:O(n)
    fun findMaxLength(nums: IntArray): Int {
        var ret = 0
        var cnt = 0
        val lookup = hashMapOf(0 to -1)
        for ((i, n) in nums.withIndex()) {
            if (n == 1) {
                cnt++
            } else {
                cnt--
            }
            if (lookup.containsKey(cnt)) {
                ret = java.lang.Math.max(ret, i - (lookup[cnt] ?: 0))
            } else {
                lookup[cnt] = i
            }
        }
        return ret
    }
}

fun main(args: Array<String>) {
    // LC OJ Passed
    // see [ContiguousArrayTest] for unit test
}