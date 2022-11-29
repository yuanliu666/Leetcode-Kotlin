/**
 * You are given n points in the plane that are all distinct, where points[i] = [xi, yi].
 * A boomerang is a tuple of points (i, j, k) such that the distance
 * between i and j equals the distance between i and k (the order of the tuple matters).
 * Return the number of boomerangs.
 */
class Solution_number_of_boomeranges {

    // T:O(n^2) S:O(n)
    fun numberOfBoomerangs(points: Array<IntArray>): Int {
        var cnt = 0
        for (i in points.indices) {
            // map of distance^2 - count
            val map = hashMapOf<Int, Int>()
            for (j in points.indices) {
                if (i == j) continue
                val d = getDistanceSquare(points[i], points[j])
                map.putIfAbsent(d, 0)
                map[d] = map[d]!! + 1
                val n = map[d]!!
                // It's not hard to see that for the same distance count of n, result will add (n-1)*n
                // Rather than going over map again, we update the value in the go
                // To subtract previous value and replace with new value, that is cnt = cnt - (n-2)(n-1)+n*(n-1)
                cnt += 2 * (n - 1)
            }
        }
        return cnt
    }

    // We don't need sqrt, as long as squares are equal, distances will be equal
    private fun getDistanceSquare(p1: IntArray, p2: IntArray): Int {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1])
    }
}