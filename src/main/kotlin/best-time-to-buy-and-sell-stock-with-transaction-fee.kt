/**
 * Your are given an array of integers prices, for which the i-th element is
 * the price of a given stock on day i; and a non-negative integer fee representing a transaction fee.
 *
 * You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.
 * You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)
 *
 * Return the maximum profit you can make.
 *
 * Example 1:
 * Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * Output: 8
 * Explanation: The maximum profit can be achieved by:
 * Buying at prices[0] = 1
 * Selling at prices[3] = 8
 * Buying at prices[4] = 4
 * Selling at prices[5] = 9
 * The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * Note:
 *
 * 0 < prices.length <= 50000.
 * 0 < prices\[i] < 50000.
 * 0 <= fee < 50000.
 */

class Solution_best_time_to_buy_and_sell_stock_with_transaction_fee {

    // T:O(n) S:O(1)

    // dp formula:
    // let buy[i] denotes max profit at p[i] with end action of buy
    // sell[i] denotes max profit at p[i] with end action of sell
    // based on whether buy/sell at p[i]
    // buy[i+1] = max(buy[i], sell[i] - p[i] - fee)
    // sell[i+1] = max(sell[i], buy[i] + p[i])
    // and we can reduce space to O(1) here
    fun maxProfit(prices: IntArray, fee: Int): Int {
        if (prices.size <= 1) {
            return 0
        }
        var sell = 0
        // it's OK to assign the fee on either sell or buy, just make it consistent with init values
        // here we assign fee on buy
        var buy = -prices[0] - fee
        for (i in 1 until prices.size) {
            val prevBuy = buy
            buy = maxOf(sell - prices[i] - fee, buy)
            sell = maxOf(prevBuy + prices[i], sell)
        }
        return sell
    }
}

fun main(args: Array<String>) {
    // LC OJ passed
    val s = Solution_best_time_to_buy_and_sell_stock_with_transaction_fee()
    print(s.maxProfit(intArrayOf(1, 3, 2, 8, 4, 9), 2))
}