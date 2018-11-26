/**
 * There is a box protected by a password.
 * The password is n digits, where each letter can be one of the first k digits 0, 1, ..., k-1.
 *
 * You can keep inputting the password, the password will automatically be matched
 * against the last n digits entered.
 *
 * For example, assuming the password is "345", I can open it when I type "012345",
 * but I enter a total of 6 digits.
 *
 * Please return any string of minimum length that is guaranteed to open the box
 * after the entire string is inputted.
 *
 * Example 1:
 * Input: n = 1, k = 2
 * Output: "01"
 * Note: "10" will be accepted too.
 *
 * Example 2:
 * Input: n = 2, k = 2
 * Output: "00110"
 * Note: "01100", "10011", "11001" will be accepted too.
 *
 * Note:
 * 1. n will be in the range [1, 4].
 * 2. k will be in the range [1, 10].
 * 3. k^n will be at most 4096.
 */

class Solution_cracking_the_safe {

    // DFS
    // T: O(n * k^n)
    // S: O(n * k^n)
    // Note: there is a solution with O(k^n) but it acquires knowledge of De Bruijn sequence and
    // Lyndon word.
    fun crackSafe(n: Int, k: Int): String {
        val ret = mutableListOf<Int>()
        for (i in 0 until n - 1) {
            ret.add(k - 1)
        }
        dfs(k, ret, ret.joinToString(""), mutableSetOf())
        return ret.joinToString("")
    }

    private fun dfs(k: Int, ret: MutableList<Int>, buff: String, lookup: MutableSet<String>) {
        for (i in 0 until k) {
            val s = buff + i.toString()
            if (s !in lookup) {
                ret.add(i)
                lookup.add(s)
                dfs(k, ret, s.substring(1), lookup)
                break
            }
        }
    }
}

fun main(args: Array<String>) {

}