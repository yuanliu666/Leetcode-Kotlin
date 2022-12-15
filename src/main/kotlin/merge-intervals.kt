/**
 * Given an array of intervals where intervals[i] = [starti, endi],
 * merge all overlapping intervals, and return an array of the non-overlapping intervals
 * that cover all the intervals in the input.
 */
class Solution_merge_intervals {

    // T:O(nlogn) S:O(n)
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        if (intervals.isEmpty()) return emptyArray()

        intervals.sortWith(compareBy { it[0] })
        var cur = intervals[0]
        val ret = mutableListOf<IntArray>()
        for (i in 1 until intervals.size) {
            cur = if (intervals[i][0] <= cur[1]) {
                intArrayOf(cur[0], kotlin.math.max(cur[1], intervals[i][1]))
            } else {
                ret.add(cur)
                intervals[i]
            }
        }
        ret.add(cur)
        return ret.toTypedArray()
    }
}