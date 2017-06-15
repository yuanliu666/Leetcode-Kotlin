/**
 * Given a non-negative integer num, repeatedly add
 * all its digits until the result has only one digit.
 *
 * For example:
 *
 * Given num = 38, the process is like: 3 + 8 = 11,
 * 1 + 1 = 2. Since 2 has only one digit, return it.
 *
 * Follow up:
 * Could you do it without any loop/recursion in O(1)
 * runtime?
 *
 * Hint:
 *
 * A naive implementation of the above process is trivial.
 * Could you come up with other methods?
 */

class Solution_add_digits {

    // T:O(1) S:O(1)
    fun addDigits(n: Int): Int {
        return if (n == 0) 0 else (n - 1) % 9 + 1
    }
}

fun main(args: Array<String>) {
    println(Solution_add_digits().addDigits(38))
}
