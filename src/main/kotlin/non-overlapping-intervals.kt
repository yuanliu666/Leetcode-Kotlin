/**
 * Given an array of intervals intervals where intervals[i] = [starti, endi],
 * return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 */
class Solution_non_overlapping_intervals {

    // Converting the problem to (# of intervals) - "Given a collection of intervals, find the maximum number of intervals that are non-overlapping"
    // For that question, we sort intervals by end time, then just grab non-overlapping intervals
    // T:O(nlgn) S:O(1)
    fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {
        intervals.sortWith(compareBy { it[1] })
        // grab first interval
        var cnt = 1
        var end = intervals[0][1]
        for (i in 1 until intervals.size) {
            // find next non-overlapping
            if (intervals[i][0] >= end) {
                end = intervals[i][1]
                cnt++
            }
        }
        return intervals.size - cnt
    }
}