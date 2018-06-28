/**
 * Given a collection of candidate numbers (C) and a target number (T),
 * find all unique combinations in C where the candidate numbers sums to T.
 *
 * Each number in C may only be used once in the combination.
 *
 * Note:
 * All numbers (including target) will be positive integers.
 * Elements in a combination (a1, a2, ... , ak) must be in non-descending order. (ie, a1 <= a2 <= ... <= ak).
 * The solution set must not contain duplicate combinations.
 * For example, given candidate set 10,1,2,7,6,1,5 and target 8,
 * A solution set is:
 * [1, 7]
 * [1, 2, 5]
 * [2, 6]
 * [1, 1, 6]
 */

class Solution_combination_sum_ii {

    // T:O(k * C(n, k)) S:O(k)
    fun getCombinationSumSet(n: Array<Int>, t: Int): Set<List<Int>> {
        val ret = mutableSetOf<List<Int>>()
        helper(0, t, mutableListOf(), ret, n.sortedArray())
        return ret
    }

    private fun helper(start: Int, t: Int, temp: MutableList<Int>, ret: MutableSet<List<Int>>, n: Array<Int>) {
        if (t < 0 || start > n.size - 1) {
            return
        } else if (t == 0) {
            val r = mutableListOf<Int>()
            r.addAll(temp)
            ret.add(r)
            return
        }
        for (i in start.until(n.size - 1)) {
            temp.add(n[i])
            helper(i + 1, t - n[i], temp, ret, n)
            temp.remove(n[i])
        }
    }
}

fun main(args: Array<String>) {
    // LC OJ Passed
    // see [CombinationSumIITest] for unit test
}