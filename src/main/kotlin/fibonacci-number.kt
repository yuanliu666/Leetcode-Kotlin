/**
 * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence,
 * such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,
 * F(0) = 0, F(1) = 1
 * F(n) = F(n - 1) + F(n - 2), for n > 1.
 * Given n, calculate F(n).
 */
class Solution_fibonacci_number {

    // T:O(n) S:O(n)
    fun fib(n: Int): Int {
        if (n == 0) return 0
        if (n == 1) return 1
        val dp = IntArray(n + 1)
        dp[0] = 0
        dp[1] = 1
        for (i in 2..n) {
            dp[i] = dp[i - 1] + dp[i - 2]
        }
        return dp[n]
    }
}