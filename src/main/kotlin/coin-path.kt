/**
 * Given an array A (index starts at 1) consisting of N integers: A1, A2, ..., AN and an integer B.
 * The integer B denotes that from any place (suppose the index is i) in the array A,
 * you can jump to any one of the place in the array A indexed i+1, i+2, …, i+B if this place can be jumped to.
 * Also, if you step on the index i, you have to pay Ai coins.
 * If Ai is -1, it means you can’t jump to the place indexed i in the array.
 *
 * Now, you start from the place indexed 1 in the array A,
 * and your aim is to reach the place indexed N using the minimum coins.
 * You need to return the path of indexes (starting from 1 to N) in the array you should take to get to
 * the place indexed N using minimum coins.
 *
 * If there are multiple paths with the same cost, return the lexicographically smallest such path.
 *
 * If it's not possible to reach the place indexed N then you need to return an empty array.
 *
 * Example 1:
 * Input: [1,2,4,-1,2], 2
 * Output: [1,3,5]
 *
 * Example 2:
 * Input: [1,2,4,-1,2], 1
 * Output: []
 *
 * Note:
 * Path Pa1, Pa2, ..., Pan is lexicographically smaller than Pb1, Pb2, ..., Pbm,
 * if and only if at the first i where Pai and Pbi differ, Pai < Pbi; when no such i exists, then n < m.
 * A1 >= 0. A2, ..., AN (if exist) will in the range of [-1, 100].
 * Length of A is in the range of [1, 1000].
 * B is in the range of [1, 100].
 */

class Solution_coin_path {
    // Let dp[N] denotes min coins to reach index N, so it can be calculated based on dp values from steps(B) before, namely:
    // dp[N] = min(dp[N], dp[N-1]+A[N-1], ..., dp[N-B]+A[N-B])
    // While we also need to take care of the path
    // A simple idea will be letting each dp also holds a path list, but that's of too low space efficiency
    // Instead, we can just track one step before
    // Namely, dp[N] = (p, c), where p is the min index used before, and c is the min coins to reach index N
    // The order will be taken care of by the traversal sequence

    // T:O(n*B) S:O(n)
    fun getMinCoins(coins: Array<Int>, step: Int): List<Int> {
        if (coins.size == 1) {
            return emptyList()
        }
        val dp = Array(coins.size, { Pair(0, Int.MAX_VALUE) })
        dp[0] = Pair(0, 0)
        for (i in 0 until coins.size) {
            for (j in 1..step) {
                if (coins[i] != -1 && i + j < coins.size && coins[i + j] != -1) {
                    if (dp[i].second + coins[i] < dp[i + j].second) {
                        dp[i + j] = Pair(i, minOf(dp[i + j].second, dp[i].second + coins[i]))
                    }
                }
            }
        }
        return if (dp.last().second == Int.MAX_VALUE) {
            emptyList()
        } else {
            var idx = coins.size - 1
            val ret = mutableListOf(idx + 1)
            while (idx > 0) {
                ret.add(dp[idx].first + 1)
                idx = dp[idx].first
            }
            ret.reversed()
        }
    }
}

fun main(args: Array<String>) {
    // see [CoinPathTest] for unit test
}