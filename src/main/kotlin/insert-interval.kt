/**
 * You are given an array of non-overlapping intervals intervals
 * where intervals[i] = [starti, endi] represent the start and the end of the ith interval
 * and intervals is sorted in ascending order by starti.
 * You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti
 * and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
 * Return intervals after the insertion.
 */
class Solution_insert_interval {

    // T:O(n), S:O(n)
    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        val list = intervals.toMutableList()
        // Insert new interval and keep start sorted
        for (i in list.indices) {
            if (newInterval[0] <= list[i][0]) {
                list.add(i, newInterval)
                break
            }
        }
        // Append at end if larger than any element
        if (list.size == intervals.size) {
            list.add(newInterval)
        }

        // Merge overlapped intervals
        val ret = mutableListOf<IntArray>()
        var cur = list[0]
        for (i in 1 until list.size) {
            cur = if (isOverlap(cur, list[i])) {
                merge(cur, list[i])
            } else {
                ret.add(cur)
                list[i]
            }
        }
        ret.add(cur)
        return ret.toTypedArray()
    }

    // Assume a is before b since intervals are sorted
    private fun isOverlap(a: IntArray, b: IntArray): Boolean {
        return b[0] <= a[1]
    }

    // Assume a and b are overlapped
    private fun merge(a: IntArray, b: IntArray): IntArray {
        return intArrayOf(a[0], kotlin.math.max(a[1], b[1]))
    }
}