/**
 * Given an integer, write an algorithm to convert it to hexadecimal.
 * For negative integer, two’s complement method is used.
 *
 * Note:
 *
 * 1. All letters in hexadecimal (a-f) must be in lowercase.
 * 2. The hexadecimal string must not contain extra leading 0s.
 * If the number is zero, it is represented by a single zero character '0';
 * otherwise, the first character in the hexadecimal string will not be the zero character.
 * 3. The given number is guaranteed to fit within the range of a 32-bit signed integer.
 * 4. You must not use any method provided by the library which converts/formats the number to hex directly.
 *
 * Example 1:
 *
 * Input:
 * 26
 *
 * Output:
 * "1a"
 *
 * Example 2:
 *
 * Input:
 * -1
 *
 * Output:
 * "ffffffff"
 */

class Solution_convert_a_number_to_hexadecimal {

    // T:O(logn), S:O(1)
    fun toHex(num: Int): String {
        if (num == 0) {
            return "0"
        }
        var ret = ""
        var n = num
        while (n != 0 && ret.length < 8) {
            val h: Int = n and 15
            ret += if (h < 10) {
                h.toString()
            } else {
                ('a'.toInt() + h - 10).toChar().toString()
            }
            n = n.shr(4)
        }
        return ret.reversed()
    }
}

fun main(args: Array<String>) {
    // LC OJ Passed
    // see [ConvertANumberToHexadecimalTest] for unit test
}