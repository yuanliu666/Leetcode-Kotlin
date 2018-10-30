/**
 * Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10^n.
 *
 * Example:
 *
 * Input: 2
 * Output: 91
 *
 * Explanation: The answer should be the total numbers in the range of 0 ≤ x < 100,
 * excluding 11,22,33,44,55,66,77,88,99
 */

class Solution_count_numbers_with_unique_digits {

    private val factorialMap = mutableMapOf(
            0 to 1,
            1 to 1,
            2 to 2,
            3 to 6,
            4 to 24,
            5 to 120,
            6 to 720,
            7 to 5040,
            8 to 40320,
            9 to 362880,
            10 to 3628800)

    // T: O(n) ~= O(1)
    // S: O(1)
    fun countNumbersWithUniqueDigits(n: Int): Int {
        // basic observation: when n > 10 this kind of number does not exist
        if (n > 10) return countNumbersWithUniqueDigits(10)
        return when (n) {
            0 -> 1
            1 -> 10
            2 -> 91
        // previous results, plus new n-digit ones,
        // which is permutations of n out of 0-9 except those ones starting with 0
            else -> getPermutation(n, 10) - getPermutation(n - 1, 9) + countNumbersWithUniqueDigits(n - 1)
        }
    }

    // T:O(1)
    // get A(m, n) where n >= m
    private fun getPermutation(m: Int, n: Int): Int {
        return factorialMap[n]!! / factorialMap[n - m]!!
    }
}

fun main(args: Array<String>) {

}