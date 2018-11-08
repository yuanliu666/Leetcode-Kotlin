/**
 * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num
 * calculate the number of 1's in their binary representation and return them as an array.
 *
 * Example 1:
 *
 * Input: 2
 * Output: [0,1,1]
 * Example 2:
 *
 * Input: 5
 * Output: [0,1,1,2,1,2]
 *
 * Follow up:
 *
 * 1. It is very easy to come up with a solution with run time O(n*sizeof(integer)).
 * But can you do it in linear time O(n) /possibly in a single pass?
 * Space complexity should be O(n).
 *
 * 2. Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++
 * or in any other language.
 */

class Solution_counting_bits {

    // technically, as an integer will have at most 32 bits, each integer takes O(1) and result is still O(n)
    // but we can do better than that
    // T:O(n) S:O(n)
    fun countBits(num: Int): IntArray {
        val ret = IntArray(num + 1, { 0 })
        for (i in 0 until ret.size) {
            ret[i] = ret[i.shr(1)] + if (i and 1 == 1) 1 else 0
        }
        return ret
    }
}

fun main(args: Array<String>) {

}