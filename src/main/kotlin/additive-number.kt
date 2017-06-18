/**
 * Additive number is a positive integer whose digits can form additive sequence.
 *
 * A valid additive sequence should contain at least three numbers.
 * Except for the first two numbers, each subsequent number in the sequence
 * must be the sum of the preceding two.
 *
 * For example:
 * "112358" is an additive number because the digits can form an additive sequence:
 *   1, 1, 2, 3, 5, 8.
 *
 * 1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 * "199100199" is also an additive number, the additive sequence is:
 * 1, 99, 100, 199.
 *
 * 1 + 99 = 100, 99 + 100 = 199
 * Note: Numbers in the additive sequence cannot have leading zeros,
 * so sequence 1, 2, 03 or 1, 02, 3 is invalid.
 *
 * Given a string represents an integer, write a function to determine
 * if it's an additive number.
 *
 * Follow up:
 * How would you handle overflow for very large input integers?
 */

class Solution_additive_number {

    // help function for large numbers addition
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

    // T:O(n^3) S:O(n)
    fun isAdditiveNumber(s: String): Boolean {

        var first: String
        var second: String
        var sum: String

        for (i in 0..s.length - 3) {
            for (j in i + 1..s.length - 2) {
                first = s.substring(0..i)
                second = s.substring(i + 1..j)
                if ((second.length > 1 && second.startsWith("0")) ||
                        (first.length > 1 && first.startsWith("0")))
                    break

                sum = addStrings(first, second)
                var cur = first + second + sum
                while (cur.length < s.length) {
                    first = second
                    second = sum
                    sum = addStrings(first, second)
                    cur += sum
                }
                if (cur == s)
                    return true
            }
        }

        return false
    }
}

fun main(args: Array<String>) {
    val s = Solution_additive_number()
    println(s.isAdditiveNumber("112358"))
    println(s.isAdditiveNumber("199100199"))
    println(s.isAdditiveNumber("1203"))
}
