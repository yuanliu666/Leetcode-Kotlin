/**
 * Given a list of non-negative numbers and a target integer k, write a function to check
 * if the array has a continuous subarray of size at least 2 that sums up to the multiple of k,
 * that is, sums up to n*k where n is also an integer.
 *
 * Example 1:
 * Input: [23, 2, 4, 6, 7],  k=6
 * Output: True
 * Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
 *
 * Example 2:
 * Input: [23, 2, 6, 4, 7],  k=6
 * Output: True
 * Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
 *
 * Note:
 * 1. The length of the array won't exceed 10,000.
 * 2. You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
 */

class Solution_continuous_subarray_sum {

    // Very similar to contiguous array problem
    // A little bit tricky when k <= 0 and also need to consider n <= 0
    // T:O(n) S:O(k)
    fun checkSubarraySum(nums: IntArray, k: Int): Boolean {
        // reminder to idx map
        // dummy point to handle case like nums = [5, 5] and k = 5
        val map = hashMapOf(0 to -1)
        var cnt = 0
        for ((i, n) in nums.withIndex()) {
            cnt += n
            if (k != 0) {
                cnt %= k
            }
            if (cnt in map) {
                // avoid single element case like nums = [1, 5, 1] and 5
                if (i - (map[cnt] ?: 0) > 1) {
                    return true
                }
            } else {
                map[cnt] = i
            }
        }
        return false
    }
}

fun main(args: Array<String>) {
    // LC OJ Passed
    // see [ContinuousSubarraySumTest] for unit test.
}