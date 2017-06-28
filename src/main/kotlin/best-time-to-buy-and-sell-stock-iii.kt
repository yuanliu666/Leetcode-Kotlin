/**
 * Say you have an array for which the ith element
 * is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit.
 * You may complete at most two transactions.
 *
 * Note:
 * You may not engage in multiple transactions at the same time
 * (ie, you must sell the stock before you buy again).
 */

class Solution_best_time_to_buy_and_sell_stock_iii {
    // This is actually a simplified version of iv
    // sell_1 is the best profit we can get so far when sell the first stock
    // buy_1 for buy
    // same for sell_2 and buy_2
    // T:O(n) S:O(1)
    fun getMaxProfit(prices: Array<Int>): Int {
        var buy_1 = Int.MIN_VALUE
        var buy_2 = Int.MIN_VALUE
        var sell_1 = 0
        var sell_2 = 0
        for (v in prices) {
            // we can get the formula by considering two situations: to do (sell or buy) or not to do
            // the dependency decides the sequence - it's NOT arbitrary
            sell_2 = maxOf(sell_2, buy_2 + v)
            buy_2 = maxOf(buy_2, sell_1 - v)
            sell_1 = maxOf(sell_1, buy_1 + v)
            buy_1 = maxOf(buy_1, -v)
        }
        return sell_2
    }
}

fun main(args: Array<String>) {
    val s = Solution_best_time_to_buy_and_sell_stock_iii()
    println(s.getMaxProfit(arrayOf(3, 2, 1, 4, 2, 5, 6)))
}