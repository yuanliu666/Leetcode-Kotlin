/**
 * Count the number of prime numbers less than a non-negative number, n.
 *
 * Example:
 *
 * Input: 10
 * Output: 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 */

class Solution_count_primes {

    // T:O(n) S:O(n)
    // tuned in the reference of
    // https://leetcode.com/problems/count-primes/discuss/57593/12-ms-Java-solution-modified-from-the-hint-method-beats-99.95
    fun countPrimes(n: Int): Int {
        return when {
            n < 3 -> 0
            else -> {
                // Start with the assumption that half the numbers below n are prime candidates
                // we just need to subtract those odd composite numbers
                var cnt = n / 2
                // initializes boolean arrays to {false}
                // use truth to mark _composite_ numbers
                val s = BooleanArray(n)
                // skip evens, i is always odd
                // also no need to go beyond sqrt(n)
                for (i in 3 until (java.lang.Math.sqrt(n.toDouble()).toInt() + 1) step 2) {
                    if (s[i]) continue
                    // For each prime i, iterate through the odd composites
                    for (j in i * i until n step 2 * i) {
                        if (!s[j]) {
                            cnt--
                            s[j] = true
                        }
                    }
                }
                cnt
            }
        }
    }
}

fun main(args: Array<String>) {

}