/**
 * You are given coins of different denominations and
 * a total amount of money amount. Write a function to
 * compute the fewest number of coins that you need to
 * make up that amount. If that amount of money cannot
 * be made up by any combination of the coins, return -1.
 *
 * Example 1:
 * coins = [1, 2, 5], amount = 11
 * return 3 (11 = 5 + 5 + 1)
 *
 * Example 2:
 * coins = [2], amount = 3
 * return -1.
 *
 * Note:
 * You may assume that you have an infinite number of each kind of coin.
 */

class Solution_coin_change {
    // Greedy will not work out here. For example, [1, 3, 4, 5] with amount of 7.
    // Instead we should consider DP.

    // T:O(n*k) S:O(k)
    fun minCoins(coins: Array<Int>, a: Int): Int {
        val dp = Array(a + 1, { Int.MAX_VALUE })
        dp[0] = 0
        for (i in 0..a - 1) {
            for (j in coins) {
                if (i + j <= a) {
                    dp[i + j] = minOf(dp[i + j], dp[i] + 1)
                }
            }
        }
        if (dp.last() == Int.MAX_VALUE) {
            return -1
        } else {
            return dp.last()
        }
    }
}

fun main(args: Array<String>) {
    val s = Solution_coin_change()
    println(s.minCoins(arrayOf(1, 2, 5), 11))
    println(s.minCoins(arrayOf(1, 3, 4, 5), 7))
    println(s.minCoins(arrayOf(3, 4, 5), 1))
}