/**
 * Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).
 * Return the running sum of nums.
 *
 * 1 <= nums.length <= 1000
 * -10^6 <= nums[i] <= 10^6
 */

class Solution_running_sum_of_1d_array {

    // T:O(n) S:O(n), can be done in place for S:O(1)
    fun runningSum(nums: IntArray): IntArray {
        val rs = IntArray(nums.size)
        for (i in nums.indices) {
            rs[i] = nums[i]
            if (i > 0) {
                rs[i] += rs[i - 1]
            }
        }
        return rs
    }
}