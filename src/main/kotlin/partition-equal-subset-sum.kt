/**
 * Given a non-empty array nums containing only positive integers,
 * find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 *
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 */

class Solution_partition_equal_subset_sum {

    // T:O(n * sum) S:O(sum)
    fun canPartition(nums: IntArray): Boolean {
        val sum = nums.sum()
        if (sum % 2 == 1) return false
        val t = sum / 2
        val dp = BooleanArray(t + 1) {false}
        // DP[i] means if we can get sum of i
        dp[0] = true
        for (i in nums.indices) {
            // Need to go backwards so this round's result won't affect calculation
            for (j in (1 until dp.size).reversed()) {
                if (j - nums[i] >= 0) {
                    dp[j] = dp[j] || dp[j - nums[i]]
                }
            }
        }
        return dp[t]
    }
}