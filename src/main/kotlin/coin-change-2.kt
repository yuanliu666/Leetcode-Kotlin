/**
 * You are given coins of different denominations and a total amount of money.
 * Write a function to compute the number of combinations that make up that amount.
 * You may assume that you have infinite number of each kind of coin.
 *
 * Note: You can assume that
 *
 * 0 <= amount <= 5000
 * 1 <= coin <= 5000
 * the number of coins is less than 500
 * the answer is guaranteed to fit into signed 32-bit integer
 *
 * Example 1:
 *
 * Input: amount = 5, coins = [1, 2, 5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 *
 * Example 2:
 *
 * Input: amount = 3, coins = [2]
 * Output: 0
 * Explanation: the amount of 3 cannot be made up just with coins of 2.
 *
 * Example 3:
 *
 * Input: amount = 10, coins = [10]
 * Output: 1
 */

class Solution_coin_change_2 {

    // DP solution
    // let d[i] denotes # of ways to get amount of i
    // clearly we have some sort of relationship like dp[i] += dp[i-coin]
    // and to make it consistent, let dp[0] = 1 so dp[coin] starts with 1
    // question is: how to avoid duplicates?
    // for example, with coins=[1,2] and amount=3, there should be 2 ways: (1,1,1) and (1,2)
    // and easily dp[1]=1 and dp[2]=2, so dp[1]+dp[2]=3 but actually dp[3]=2
    // this is because (1,2) and (2,1) are both counted when we simply use dp[1]+dp[2]
    // the solution is to keep coin in ascending order and consider separately:
    // the first time we only consider coin=1, dp[1]=dp[2]=dp[3]=1
    // then coin=2, here only consider with gap of 2: dp[2]=dp[2]+dp[0]=2,dp[3]=dp[1]+dp[3]=2
    // abstractly, each time we introduce a new coin so solutions never overlap with previous ones

    // T:O(m * n) S:O(m) where m is amount, n is size of coins
    fun change(amount: Int, coins: IntArray): Int {
        val dp = IntArray(amount + 1, { 0 })
        dp[0] = 1
        for (coin in coins) {
            for (i in coin.rangeTo(amount)) {
                dp[i] += dp[i - coin]
            }
        }
        return dp.last()
    }
}

fun main(args: Array<String>) {
    // LC OJ passed
    // see [CoinChange2Test] for unit test
}