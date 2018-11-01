/**
 * You are given an integer array nums and you have to return a new counts array.
 * The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 *
 * Example:
 *
 * Input: [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 */

class Solution_count_of_smaller_numbers_after_self {

    // this is very similar with last problem: we need to count during the process of merge sort
    // the only problem is that the answer requires order, so we need to keep track of original indexes
    // T:O(nlogn) S:O(n)
    fun countSmaller(nums: IntArray): List<Int> {
        val ret = IntArray(nums.size, { 0 })
        // list of num-idx pair
        val numIdx = mutableListOf<Pair<Int, Int>>()
        for ((idx, n) in nums.withIndex()) {
            numIdx.add(Pair(n, idx))
        }
        mergeSortAndCount(numIdx, 0, nums.size, ret)
        return ret.toList()
    }

    private fun mergeSortAndCount(numIdx: MutableList<Pair<Int, Int>>, s: Int, e: Int, ret: IntArray) {
        if (e - s <= 1) return
        val m = s + (e - s).shr(1)
        mergeSortAndCount(numIdx, s, m, ret)
        mergeSortAndCount(numIdx, m, e, ret)
        var r = m
        val tmp = mutableListOf<Pair<Int, Int>>()
        for (i in s until m) {
            while (r < e && numIdx[r].first < numIdx[i].first) {
                tmp.add(numIdx[r])
                r++
            }
            tmp.add(numIdx[i])
            // add cnt
            ret[numIdx[i].second] += r - m
        }
        for (i in 0 until tmp.size) {
            numIdx[s + i] = tmp[i]
        }
    }
}

fun main(args: Array<String>) {

}