/**
 * Given n balloons, indexed from 0 to n-1.
 * Each balloon is painted with a number on it
 * represented by array nums.
 * You are asked to burst all the balloons.
 * If the you burst balloon i you will get
 * nums(left) * nums(i) * nums(right) coins.
 * Here left and right are adjacent indices of i.
 * After the burst, the left and right then
 * becomes adjacent.
 *
 * Find the maximum coins you can collect by
 * bursting the balloons wisely.
 *
 * Note:
 * (1) You may imagine nums(-1) = nums(n) = 1.
 *     They are not real therefore you can not burst them.
 * (2) 0 <= n <= 500, 0 <= nums(i) <= 100
 *
 * Example:
 *
 * Given [3, 1, 5, 8]
 *
 * Return 167
 *
 *     nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *   coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */

class Solution_burst_ballon {
    // Use DP
    // Let dp[i][j] denotes the max coins we can get from index i to j, exclusively
    // To make things simple, append 1 at start and end, and now we only need to get dp[0][L-1]
    // dp[i][j] = max(for k in range[i+1,j-1], dp[i][k]+dp[k][j]+n[i]*n[k]*n[j])
    // The idea is simple: regard this k as the last balloon in range
    // We can think reversely for last shot
    // But soon we notice that the smaller ranges are needed to calculate larger ranges
    // Thus for calculation we start from small range

    // T:O(n^3) S:O(n^2)
    fun getMaxCoins(nums: Array<Int>): Int {
        val n = mutableListOf<Int>()
        n.addAll(nums)
        n.add(0, 1)
        n.add(1)
        val dp = Array(n.size, { Array(n.size, { 0 }) })
        for (diff in 2..n.size - 1) {
            for (i in 0..n.size - 1 - diff) {
                val j = i + diff
                for (k in i + 1..j - 1) {
                    dp[i][j] = maxOf(dp[i][j], dp[i][k] + dp[k][j] + n[i] * n[k] * n[j])
                }
            }
        }
        return dp[0][n.size - 1]
    }
}

fun main(args: Array<String>) {
    val s = Solution_burst_ballon()
    println(s.getMaxCoins(arrayOf(3, 1, 5, 8)))
}