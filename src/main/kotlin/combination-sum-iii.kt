/**
 * Find all possible combinations of k numbers that add up to a number n,
 * given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
 *
 * Ensure that numbers within the set are sorted in ascending order.
 *
 *
 * Example 1:
 *
 * Input: k = 3, n = 7
 *
 * Output:
 *
 * [[1,2,4]]
 *
 *
 * Example 2:
 *
 * Input: k = 3, n = 9
 *
 * Output:
 *
 * [[1,2,6], [1,3,5], [2,3,4]]
 */

class Solution_combination_sum_iii {
    fun getSets(k: Int, n: Int): Set<List<Int>> {
        val ret = mutableSetOf<List<Int>>()
        helper(k, n, 1, mutableListOf(), ret)
        return ret
    }

    private fun helper(k: Int, n: Int, start: Int, temp: MutableList<Int>, ret: MutableSet<List<Int>>) {
        if (k == 0) {
            if (n == 0) {
                val t = mutableListOf<Int>()
                t.addAll(temp)
                ret.add(t)
            }
            return
        } else if (k * 9 < n || k * start > n) {
            return
        }
        for (i in start.until(9)) {
            temp.add(i)
            helper(k - 1, n - i, i + 1, temp, ret)
            temp.remove(i)
        }
    }
}

fun main(args: Array<String>) {
    val s = Solution_combination_sum_iii()
    println(s.getSets(3, 7))
    println(s.getSets(3, 9))
}