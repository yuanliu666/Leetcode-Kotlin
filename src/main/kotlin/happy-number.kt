/**
 * Write an algorithm to determine if a number n is happy.
 * A happy number is a number defined by the following process:
 * Starting with any positive integer, replace the number by the sum of the squares of its digits.
 * Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy.
 * Return true if n is a happy number, and false if not.
 */
class Solution_happy_number {

    // T:O(logn) S:O(logn), more time/space complexity anaylsis at https://leetcode.com/problems/happy-number/solution/
    fun isHappy(n: Int): Boolean {
        val set = hashSetOf<Int>()
        var cur = n
        while (true) {
            if (cur == 1 || set.contains(cur)) break
            set.add(cur)
            cur = getNext(cur)
        }
        return cur == 1
    }

    private fun getNext(n: Int): Int {
        var ret = 0
        var cur = n
        while (cur > 0) {
            val d = cur % 10
            cur /= 10
            ret += d * d
        }
        return ret
    }
}