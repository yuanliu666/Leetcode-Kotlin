/**
 * Given two non-negative numbers num1 and num2 represented as string,
 * return the sum of num1 and num2.
 *
 * Note:
 *
 * The length of both num1 and num2 is < 5100.
 * Both num1 and num2 contains only digits 0-9.
 * Both num1 and num2 does not contain any leading zero.
 * You must not use any built-in BigInteger library or
 * convert the inputs to integer directly.
 */

class Solution_add_strings {

    // T:O(n) S:O(1)
    fun addStrings(num1: String, num2: String): String {
        var i = 0
        var j = 0
        var carry = 0
        var cur: Int
        val sb = StringBuilder()
        while (i < num1.length || j < num2.length) {
            cur = carry
            if (i < num1.length) {
                cur += num1[num1.length - 1 - i].toInt() - '0'.toInt()
                i++
            }
            if (j < num2.length) {
                cur += num2[num2.length - 1 - j].toInt() - '0'.toInt()
                j++
            }
            sb.insert(0, cur % 10)
            carry = cur / 10
        }
        if (carry > 0)
            sb.insert(0, carry)
        return sb.toString()
    }
}

fun main(args: Array<String>) {
    val s = Solution_add_strings()
    println(s.addStrings("14", "3"))
    println(s.addStrings("14", "0"))
    println(s.addStrings("14", "86"))
}
