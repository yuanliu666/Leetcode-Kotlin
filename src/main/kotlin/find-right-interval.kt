/**
 * You are given an array of intervals, where intervals[i] = [starti, endi] and each starti is unique.
 * The right interval for an interval i is an interval j such that startj >= endi and startj is minimized.
 * Note that i may equal j.
 * Return an array of right interval indices for each interval i.
 * If no right interval exists for interval i, then put -1 at index i.
 *
 * 1 <= intervals.length <= 2 * 10^4
 * intervals[i].length == 2
 * -10^6 <= starti <= endi <= 10^6
 * The start point of each interval is unique.
 */
class Solution_find_right_intervals {

    // T:O(nlgn) S:O(n)
    fun findRightInterval(intervals: Array<IntArray>): IntArray {
        val map = hashMapOf<Int, Int>()     // start-idx map
        val list = mutableListOf<Int>()     // sorted list of start
        for (i in intervals.indices) {
            map[intervals[i][0]] = i
            list.add(intervals[i][0])
        }
        list.sort()
        val ret = IntArray(intervals.size)
        for (i in ret.indices) {
            val end = intervals[i][1]
            val idx = findEqualOrLarger(end, list)
            if (idx == -1) {
                ret[i] = -1
            } else {
                ret[i] = map[list[idx]]!!
            }
        }
        return ret
    }

    // Find the first index that is larger or equal to n
    // T:O(logN) S:O(1)
    private fun findEqualOrLarger(n: Int, list: List<Int>): Int {
        if (list[list.size - 1] < n) return -1
        var left = 0
        var right = list.size - 1
        while (left < right) {
            val m = (right - left) / 2 + left
            when {
                list[m] == n -> return m
                list[m] > n -> right = m
                else -> left = m + 1
            }
        }
        return left
    }
}