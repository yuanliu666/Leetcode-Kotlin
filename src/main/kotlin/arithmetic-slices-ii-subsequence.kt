/**
 * A sequence of numbers is called arithmetic if it consists of at least three elements
 * and if the difference between any two consecutive elements is the same.
 *
 * For example, these are arithmetic sequences:
 *
 * 1, 3, 5, 7, 9
 * 7, 7, 7, 7
 * 3, -1, -5, -9
 * The following sequence is not arithmetic.
 *
 * 1, 1, 2, 5, 7
 *
 * A zero-indexed array A consisting of N numbers is given.
 * A subsequence slice of that array is any sequence of integers (P0, P1, ..., Pk)
 * such that 0 ≤ P0 < P1 < ... < Pk < N.
 *
 * A subsequence slice (P0, P1, ..., Pk) of array A is called arithmetic
 * if the sequence A_P0, A_P1, ..., A_Pk-1, A_Pk is arithmetic. In particular, this means that k >= 2.
 *
 * The function should return the number of arithmetic subsequence slices in the array A.
 *
 * The input contains N integers. Every integer is in the range of -2^31 and 2^31-1 and 0 <= N <= 1000.
 * The output is guaranteed to be less than 2^31-1.
 *
 *
 * Example:
 *
 * Input: [2, 4, 6, 8, 10]
 *
 * Output: 7
 *
 * Explanation:
 * All arithmetic subsequence slices are:
 * [2,4,6]
 * [4,6,8]
 * [6,8,10]
 * [2,4,6,8]
 * [4,6,8,10]
 * [2,4,6,8,10]
 * [2,6,10]
 */

class Solution_arithmetic_slices_ii_subsequence {
    // dp[i][diff] is max number of slices we could POSSIBLY get (depends on next element),
    // which with diff, and make arr[i] second last element
    // T:O(n^2) S:O(nd), d is the number of different diffs
    fun getSliceCnt(arr: Array<Int>): Int {
        var ret = 0
        val dp = Array(arr.size, { mutableMapOf<Int, Int>() })
        for (i in 1..arr.size - 1)
            for (j in 0..i - 1) {
                val diff = arr[i] - arr[j]
                dp[i].put(diff, dp[i].getOrDefault(diff, 1))
                if (diff in dp[j]) {
                    dp[i].put(diff, dp[i][diff]!! + dp[j][diff]!!)
                    ret += dp[j][diff]!!
                }
            }
        return ret
    }
}

fun main(args: Array<String>) {
    val s = Solution_arithmetic_slices_ii_subsequence()
    println(s.getSliceCnt(arrayOf(2, 4, 6, 8, 10)))
    println(s.getSliceCnt(arrayOf(2, 4, 4, 6)))
    println(s.getSliceCnt(arrayOf(2, 2, 3, 4)))
}
