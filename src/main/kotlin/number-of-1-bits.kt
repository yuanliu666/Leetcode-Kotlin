/**
 * Write a function that takes an unsigned integer and returns the number of '1' bits it has (also known as the Hamming weight).
 * Note:
 * Note that in some languages, such as Java, there is no unsigned integer type.
 * In this case, the input will be given as a signed integer type. It should not affect your implementation,
 * as the integer's internal binary representation is the same, whether it is signed or unsigned.
 * In Java, the compiler represents the signed integers using 2's complement notation.
 * Therefore, in Example 3, the input represents the signed integer. -3.
 *
 * Example 3:
 * Input: n = 11111111111111111111111111111101
 * Output: 31
 * Explanation: The input binary string 11111111111111111111111111111101 has a total of thirty one '1' bits.
 *
 * Constraints:
 * The input must be a binary string of length 32.
 *
 * Follow up: If this function is called many times, how would you optimize it?
 */
class Solution_number_of_1_bits {

    // Bit manipulation. T:O(1)
    fun hammingWeight(n: Int): Int {
        var res = 0
        var curr = n
        while (curr != 0) {
            res += curr and 1
            curr = curr ushr 1
        }
        return res
    }

    // Use built in function. T:O(1)
    fun hammingWeight2(n: Int): Int {
        return Integer.toBinaryString(n).count { it == '1' }
    }
}