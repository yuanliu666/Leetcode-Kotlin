/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed,
 * the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * Given an integer array nums representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the police.
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 */
class Solution_house_robber {

    // Basic DP solution. T:O(n) S:O(n)
    fun rob(nums: IntArray): Int {
        // dp[i] means most money robbed at index i (inclusive)
        val dp = IntArray(nums.size)
        dp[0] = nums[0]
        if (nums.size > 1) {
            dp[1] = kotlin.math.max(nums[0], nums[1])
        }
        for (i in 2 until dp.size) {
            dp[i] = kotlin.math.max(dp[i - 1], dp[i - 2] + nums[i])
        }
        return dp[dp.size - 1]
    }

    // Since we only use dp[i-1] and dp[i-2] to get dp[i], can use rolling dp of size 3
    // T:O(n) S:O(1)
    fun rob2(nums: IntArray): Int {
        if (nums.size == 1) return nums[0]
        if (nums.size == 2) return kotlin.math.max(nums[0], nums[1])

        val dp = IntArray(3)
        dp[0] = nums[0]
        dp[1] = kotlin.math.max(nums[0], nums[1])

        for (i in 2 until nums.size) {
            dp[i % 3] = kotlin.math.max(dp[(i - 1) % 3], dp[(i - 2) % 3] + nums[i])
        }
        return dp[(nums.size - 1) % 3]
    }
}