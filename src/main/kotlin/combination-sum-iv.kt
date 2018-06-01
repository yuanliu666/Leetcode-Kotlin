/**
 * Given an integer array with all positive numbers and no duplicates,
 * find the number of possible combinations that add up to a positive integer target.
 *
 * Example:
 *
 * nums = [1, 2, 3]
 * target = 4
 *
 * The possible combination ways are:
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 *
 * Note that different sequences are counted as different combinations.
 *
 * Therefore the output is 7.
 * Follow up:
 * What if negative numbers are allowed in the given array?
 * How does it change the problem?
 * What limitation we need to add to the question to allow negative numbers?
 */

class Solution_combination_sum_iv {
    // for original problem it's like coin change 2 problem without ordering
    // for follow up, an intuitive thought is that if somehow we can get sum of 0 then it might go infinite

    // T: O(nlon + n * t), where t is the value of target, n is size of nums
    // S: O(t)
    fun combinationSum4(nums: IntArray, target: Int): Int {
        // let dp[i] denotes combination to form value i
        val dp = IntArray(target + 1, { 0 })
        dp[0] = 1
        for (i in 0 until dp.size) {
            for (n in nums) {
                if (i + n < dp.size) {
                    dp[i + n] += dp[i]
                }
            }
        }
        return dp.last()
    }
}

fun main(args: Array<String>) {
    // LC OJ passed
    val s = Solution_combination_sum_iv()
    println(s.combinationSum4(intArrayOf(1, 2, 3), 4))
}