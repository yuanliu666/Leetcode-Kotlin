/**
 * Given a list of points that form a polygon when joined sequentially,
 * find if this polygon is convex (Convex polygon definition: https://en.wikipedia.org/wiki/Convex_polygon).
 *
 * Note:
 *
 * There are at least 3 and at most 10,000 points.
 * Coordinates are in the range -10,000 to 10,000.
 * You may assume the polygon formed by given points is always a simple polygon
 * (Simple polygon definition: https://en.wikipedia.org/wiki/Simple_polygon).
 * In other words, we ensure that exactly two edges intersect at each vertex,
 * and that edges otherwise don't intersect each other.
 *
 * Example 1:
 *
 * [[0,0],[0,1],[1,1],[1,0]]
 *
 * Answer: True
 *
 * Example 2:
 *
 * [[0,0],[0,10],[10,10],[10,0],[5,5]]
 *
 * Answer: False
 */

class Solution_convex_polygon {

    // geometry math problem
    // T:O(n) S:O(1)
    fun isConvexPolygon(points: List<List<Int>>): Boolean {
        val n = points.size
        var prev = 0
        var cur: Int
        for (i in 0 until n) {
            val vectors = mutableListOf<List<Int>>()
            for (j in 1..2) {
                vectors.add(listOf(points[(i + j) % n][0] - points[i][0], points[(i + j) % n][1] - points[i][1]))
            }
            cur = det(vectors)
            if (cur != 0) {
                if (cur * prev < 0) {
                    return false
                }
                prev = cur
            }
        }
        return true
    }

    private fun det(A: List<List<Int>>): Int = A[0][0] * A[1][1] - A[0][1] * A[1][0]
}

fun main(args: Array<String>) {
    // see [ConvexPolygonTest] for unit test
}