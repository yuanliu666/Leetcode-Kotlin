/**
 * Given an integer n, return the least number of perfect square numbers that sum to n.
 * A perfect square is an integer that is the square of an integer;
 * in other words, it is the product of some integer with itself.
 * For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.
 */
class Solution_perfect_squares {

    // T:O(n*sqrt(n)) S:O(n)
    fun numSquares(n: Int): Int {
        val maxSqrt: Int = sqrt(n.toDouble()).toInt()
        if (n == maxSqrt * maxSqrt) return 1

        val dp = IntArray(n + 1) {Int.MAX_VALUE}
        dp[0] = 0
        for (i in 1 .. n) {
            val sqrt = sqrt(i.toDouble()).toInt()
            for (j in 1 .. sqrt) {
                if (i - j * j >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - j * j] + 1)
                }
            }
        }
        return dp[n]
    }
}