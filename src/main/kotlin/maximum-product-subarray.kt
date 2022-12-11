/**
 * Given an integer array nums, find a subarray that has the largest product, and return the product.
 * The test cases are generated so that the answer will fit in a 32-bit integer.
 */
class Solution_maximum_product_subarray {

    // Direct DP solution. T:O(n) S:O(n)
    fun maxProduct(nums: IntArray): Int {
        // min/max value of product ending at index i
        val dp = Array(nums.size) { IntArray(2) }
        dp[0][0] = nums[0]
        dp[0][1] = nums[0]
        var ret = nums[0]
        for (i in 1 until nums.size) {
            val min = dp[i - 1][0]
            val max = dp[i - 1][1]
            dp[i][0] = kotlin.math.min(kotlin.math.min(nums[i], nums[i] * min), nums[i] * max)
            dp[i][1] = kotlin.math.max(kotlin.math.max(nums[i], nums[i] * min), nums[i] * max)
            ret = kotlin.math.max(ret, dp[i][1])
        }
        return ret
    }

    // Since dp[i] only relies on dp[i-1], can save space.
    // T:O(n) S:O(1)
    fun maxProduct2(nums: IntArray): Int {
        var min = nums[0]
        var max = nums[0]
        var ret = max
        for (i in 1 until nums.size) {
            val nextMin = kotlin.math.min(kotlin.math.min(nums[i], nums[i] * min), nums[i] * max)
            val nextMax = kotlin.math.max(kotlin.math.max(nums[i], nums[i] * min), nums[i] * max)
            min = nextMin
            max = nextMax
            ret = kotlin.math.max(ret, max)
        }
        return ret
    }
}