/**
 * Given a set of candidate numbers (C) and a target number (T),
 * find all unique combinations in C where the candidate numbers sums to T.
 *
 * The same repeated number may be chosen from C unlimited number of times.
 *
 * Note:
 * All numbers (including target) will be positive integers.
 * Elements in a combination (a1, a2, ... , ak) must be in non-descending order. (ie, a1 <= a2 <= ... <= ak).
 * The solution set must not contain duplicate combinations.
 * For example, given candidate set 2,3,6,7 and target 7,
 * A solution set is:
 * [7]
 * [2, 2, 3]
 */

class Solution_combination_sum {

    // T:O(k*n^k) S:O(k)
    fun getCombinationSet(arr: Array<Int>, t: Int): Set<List<Int>> {
        val ret = mutableSetOf<List<Int>>()
        helper(arr, 0, t, mutableListOf(), ret)
        return ret
    }

    fun helper(arr: Array<Int>, start: Int, t: Int, temp: MutableList<Int>, ret: MutableSet<List<Int>>) {
        if (t < 0 || start >= arr.size) {
            return
        } else if (t == 0) {
            val r = mutableListOf<Int>()
            r.addAll(temp)
            ret.add(r)
            return
        }
        for (i in start until arr.size) {
            temp.add(arr[i])
            helper(arr, i, t - arr[i], temp, ret)
            temp.remove(arr[i])
        }
    }
}

fun main(args: Array<String>) {
    // LC OJ Passed
    // see [CombinationSumTest] for unit test
}