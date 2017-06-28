/**
 * Say you have an array for which the ith element
 * is the price of a given stock on day i.
 *
 * If you were only permitted to complete at most one transaction
 * (ie, buy one and sell one share of the stock),
 * design an algorithm to find the maximum profit.
 */

class Solution_best_time_to_buy_and_sell_stock {
    // T:O(n) S:O(1)
    fun getMaxProfit(prices: Array<Int>): Int {
        var minBefore = prices[0]
        var ret = 0
        for (v in prices) {
            ret = maxOf(ret, v - minBefore)
            minBefore = minOf(minBefore, v)
        }
        return ret
    }
}

fun main(args: Array<String>) {
    val s = Solution_best_time_to_buy_and_sell_stock()
    println(s.getMaxProfit(arrayOf(1, 3, 2, 4)))
    println(s.getMaxProfit(arrayOf(3, 2, 1, 4, 2, 5, 6)))
}
