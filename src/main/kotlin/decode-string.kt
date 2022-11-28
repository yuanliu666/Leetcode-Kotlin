/**
 * Given an encoded string, return its decoded string.
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
 * Note that k is guaranteed to be a positive integer.
 * You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc.
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k.
 * For example, there will not be input like 3a or 2[4].
 * The test cases are generated so that the length of the output will never exceed 10^5.
 */
class Solution_decode_string {

    // T:O(n) S:O(n)
    fun decodeString(s: String): String {
        val stkNum = Stack<Int>()
        val stkStr = Stack<String>()
        var n = 0
        val sb = StringBuilder()    // Hold current level of string
        for (c in s) {
            when {
                c.isDigit() -> n = n * 10 + (c - '0')

                c.isLowerCase() -> sb.append(c)

                c == '[' -> {
                    stkNum.push(n)
                    n = 0
                    stkStr.push(sb.toString())      // Push empty string as well
                    sb.clear()
                }

                else -> {       // c == ']'
                    val num = stkNum.pop()
                    val str = sb.toString().repeat(num)
                    sb.clear()
                    sb.append(stkStr.pop())
                    sb.append(str)
                }
            }
        }
        return sb.toString()
    }
}