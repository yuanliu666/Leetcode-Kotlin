/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 *
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 */

class Solution_best_time_to_buy_and_sell_stock_iv {
    // sell[k]: max profit gained ending by selling k times
    // buy[k]: similar with sell

    // T:O(n*k) S:O(k)
    fun getMaxProfit(prices: Array<Int>, k: Int): Int {
        val sell = IntArray(k + 1, { 0 })
        val buy = IntArray(k + 1, { Int.MIN_VALUE })
        for (i in 0..prices.size - 1)
        // can only do at most k transactions, or cover entire array if k is too large
            for (j in 1..minOf(k, i / 2 + 1)) {
                buy[j] = maxOf(buy[j], sell[j - 1] - prices[i])
                sell[j] = maxOf(sell[j], buy[j] + prices[i])
            }
        return sell[k]
    }
}

fun main(args: Array<String>) {
    val s = Solution_best_time_to_buy_and_sell_stock_iv()
    println(s.getMaxProfit(arrayOf(1, 2, 3, 4), 2))
}