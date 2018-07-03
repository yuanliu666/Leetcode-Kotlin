/**
 * The count-and-say sequence is the sequence of integers with the first five terms as following:
 *
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 *
 * Given an integer n, generate the nth term of the count-and-say sequence.
 *
 * Note: Each term of the sequence of integers will be represented as a string.
 *
 * Example 1:
 *
 * Input: 1
 * Output: "1"
 *
 * Example 2:
 *
 * Input: 4
 * Output: "1211"
 */

class Solution_count_and_say {

    // T:O(n*2^n) S:O(2^n)
    fun countAndSay(n: Int): String {
        var s = "1"
        var cnt = n
        while (cnt > 1) {
            s = getNext(s)
            cnt--
        }
        return s
    }

    private fun getNext(s: String): String {
        val ret = StringBuilder()
        var start = 0
        while (start < s.length) {
            var end = start + 1
            while (end < s.length && s[start] == s[end]) {
                end++
            }
            ret.append("${end - start}${s[start]}")
            start = end
        }
        return ret.toString()
    }
}

fun main(args: Array<String>) {
    // LC OJ Passed
    val s = Solution_count_and_say()
    println(s.countAndSay(1))
    println(s.countAndSay(2))
    println(s.countAndSay(3))
    println(s.countAndSay(4))
    println(s.countAndSay(5))
}