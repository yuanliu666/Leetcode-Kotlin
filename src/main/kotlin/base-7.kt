/**
 * Given an integer, return its base 7 string representation.
 *
 * Example 1:
 * Input: 100
 * Output: "202"
 * Example 2:
 * Input: -7
 * Output: "-10"
 * Note: The input will be in range of [-1e7, 1e7].
 */

class Solution_base_7 {
    // T:O(1) S:O(1)
    fun getBase7(n: Int): String {
        if (n < 0)
            return "-" + getBase7(-n)
        if (n == 0)
            return "0"
        var m = n
        val sb = StringBuilder()
        while (m > 0) {
            sb.insert(0, m % 7)
            m /= 7
        }
        return sb.toString()
    }
}

fun main(args: Array<String>) {
    println(Solution_base_7().getBase7(100))
    println(Solution_base_7().getBase7(-7))
}