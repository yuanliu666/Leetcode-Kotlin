/**
 * You have a total of n coins that you want to form in a staircase shape,
 * where every k-th row must have exactly k coins.
 *
 * Given n, find the total number of full staircase rows that can be formed.
 *
 * n is a non-negative integer and fits within the range of a 32-bit signed integer.
 *
 * Example 1:
 *
 * n = 5
 *
 * The coins can form the following rows:
 * ¤
 * ¤ ¤
 * ¤ ¤
 *
 * Because the 3rd row is incomplete, we return 2.
 * Example 2:
 *
 * n = 8
 *
 * The coins can form the following rows:
 * ¤
 * ¤ ¤
 * ¤ ¤ ¤
 * ¤ ¤
 *
 * Because the 4th row is incomplete, we return 3.
 */

class Solution_arranging_coins {
    // use binary search
    // T:O(logn) S:O(1)
    fun getCoinRows(n: Int): Int {
        var low = 1
        var high = n
        var mid: Int
        while (low <= high) {
            mid = (high - low) / 2 + low
            when {
                mid * (mid + 1) > 2 * n -> high = mid - 1
                mid * (mid + 1) < 2 * n -> low = mid + 1
                else -> return mid
            }
        }
        return low - 1
    }
}

fun main(args: Array<String>) {
    println(Solution_arranging_coins().getCoinRows(5))
    println(Solution_arranging_coins().getCoinRows(6))
    println(Solution_arranging_coins().getCoinRows(8))
}
