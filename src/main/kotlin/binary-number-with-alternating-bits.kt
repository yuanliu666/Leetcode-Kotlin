/**
 * Given a positive integer, check whether it has alternating bits: namely,
 * if two adjacent bits will always have different values.
 *
 * Example 1:
 * Input: 5
 * Output: True
 * Explanation:
 * The binary representation of 5 is: 101
 *
 * Example 2:
 * Input: 7
 * Output: False
 * Explanation:
 * The binary representation of 7 is: 111.
 *
 * Example 3:
 * Input: 11
 * Output: False
 * Explanation:
 * The binary representation of 11 is: 1011.
 *
 * Example 4:
 * Input: 10
 * Output: True
 * Explanation:
 * The binary representation of 10 is: 1010.
 */

class Solution_binary_number_with_alternating_bits {

    // T:O(1) S:O(1)
    fun hasAlternatingBits(n: Int): Boolean {
        var c = n
        var lastBit = -1
        while (c > 0) {
            if (c % 2 == lastBit) {
                return false
            } else {
                lastBit = c % 2
            }
            c = c shr 1
        }
        return true
    }
}

fun main(args: Array<String>) {
    // LC OJ passed
    val s = Solution_binary_number_with_alternating_bits()
    println(s.hasAlternatingBits(5))
    println(s.hasAlternatingBits(7))
    println(s.hasAlternatingBits(11))
    println(s.hasAlternatingBits(10))
}