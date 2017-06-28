/**
 * Say you have an array for which the ith element is
 * the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit.
 * You may complete as many transactions as you like
 * (ie, buy one and sell one share of the stock multiple times).
 * However, you may not engage in multiple transactions at the same time
 * (ie, you must sell the stock before you buy again).
 */

class Solution_best_time_to_buy_and_sell_stock_ii {
    // T:O(n) S:O(1)
    fun getMaxProfit(prices: Array<Int>): Int {
        var ret = 0
        (1..prices.size - 1).forEach {
            ret += maxOf(0, prices[it] - prices[it - 1])
        }
        return ret
    }
}

fun main(args: Array<String>) {
    val s = Solution_best_time_to_buy_and_sell_stock_ii()
    println(s.getMaxProfit(arrayOf(3, 2, 1, 4, 2, 5, 6)))
}
