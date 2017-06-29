/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete as
 * many transactions as you like (ie, buy one and sell one share of the
 * stock multiple times) with the following restrictions:
 *
 * You may not engage in multiple transactions at the same time
 * (ie, you must sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on next day.
 * (ie, cooldown 1 day)
 * Example:
 *
 * prices = [1, 2, 3, 0, 2]
 * maxProfit = 3
 * transactions = [buy, sell, cooldown, buy, sell]
 */

class Solution_best_time_to_buy_and_sell_stock_with_cooldown {
    // T:O(n) S:O(1)
    fun getMaxProfit(prices: Array<Int>): Int {
        val buy = arrayOf(Int.MIN_VALUE, Int.MIN_VALUE, Int.MIN_VALUE)
        val sell = arrayOf(0, 0, 0)
        for (i in 0..prices.size - 1) {
            buy[i % 3] = maxOf(buy[(i + 2) % 3], sell[(i + 1) % 3] - prices[i])
            sell[i % 3] = maxOf(sell[(i + 2) % 3], buy[(i + 2) % 3] + prices[i])
        }
        return sell[(prices.size - 1) % 3]
    }
}

fun main(args: Array<String>) {
    val s = Solution_best_time_to_buy_and_sell_stock_with_cooldown()
    println(s.getMaxProfit(arrayOf(1, 2, 3, 0, 2)))
    println(s.getMaxProfit(arrayOf(1, 2)))
    println(s.getMaxProfit(arrayOf(2, 1)))
}
