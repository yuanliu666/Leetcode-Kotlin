/**
 * Given a range [m, n] where 0 <= m <= n <= 2147483647,
 * return the bitwise AND of all numbers in this range, inclusive.
 *
 * For example, given the range [5, 7], you should return 4.
 */

class Solution_bitwise_and_of_numbers_range {
    // The problem equals to finding the common part from min to max in binary.
    // So we get the difference of min and max, and count its bits.
    // Then we just set those bits to 0s for m&n.
    // Why need m&n, not just n? To get rid left side bits if n has higher bits.
    // T:O(1) S:O(1)
    fun getAnd(m: Int, n: Int): Int {
        var i = 0
        var diff = n - m
        while (diff > 0) {
            diff = diff shr 1
            i++
        }
        return n and m shr i shl i
    }
}

fun main(args: Array<String>) {
    val s = Solution_bitwise_and_of_numbers_range()
    println(s.getAnd(5, 7))
}