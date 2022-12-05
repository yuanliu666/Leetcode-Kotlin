/**
 * There are some spherical balloons taped onto a flat wall that represents the XY-plane.
 * The balloons are represented as a 2D integer array points
 * where points[i] = [xstart, xend] denotes a balloon whose horizontal diameter stretches between xstart and xend.
 * You do not know the exact y-coordinates of the balloons.
 * Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis.
 * A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend.
 * There is no limit to the number of arrows that can be shot.
 * A shot arrow keeps traveling up infinitely, bursting any balloons in its path.
 * Given the array points, return the minimum number of arrows that must be shot to burst all balloons.
 */
class Solution_minimum_number_of_arrows_to_burst_balloons {

    // Similar to the problem of finding max non-overlapping intervals count.
    // If we sort points by end, get first point and shoot at end,
    // then every overlapping ones will be bursted, because they will have larger ends.
    // We just grab next non-overlapping point to this and continue.
    // T:O(nlogn) S:O(1)
    fun findMinArrowShots(points: Array<IntArray>): Int {
        // sort by end
        points.sortWith(compareBy { it[1] })
        var cnt = 1
        var end = points[0][1]
        for (i in 1 until points.size) {
            val p = points[i]
            if (p[0] > end) {
                cnt++
                end = p[1]
            }
        }
        return cnt
    }
}