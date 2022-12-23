/**
 * You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].
 * The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|,
 * where |val| denotes the absolute value of val.
 * Return the minimum cost to make all points connected.
 * All points are connected if there is exactly one simple path between any two points.
 *
 * Constraints:
 * 1 <= points.length <= 1000
 * -10^6 <= xi, yi <= 10^6
 * All pairs (xi, yi) are distinct.
 */
class Solution_min_cost_to_connect_all_points {

    // Prim's Algorithm for Minimum Spanning Tree (MST):
    // Starting from a random point i, at each step,
    // choose a point that is not in MST and has least distance to MST to join MST.
    // So the key is: how to get next minimum distance node?
    // A naive idea will be calculate two sets of points (MST vs non-MST) and pick the smallest, which will be O(n^3).
    // Using min heap can help to reduce to O(n^2*logn).
    // Here's a better solution: keep an array of min distance to current MST node, and update with new MST nodes.
    // Take node i for example, it will calculate its min distance against all MST nodes,
    // thus its updated value will be the min distance to entire MST, cause min(a, min(b, c)) = min(a,b,c).
    // T:O(n^2) S:O(n)
    fun minCostConnectPoints(points: Array<IntArray>): Int {
        val n = points.size
        var ret = 0
        val inMST = BooleanArray(n)
        val minDist = IntArray(n) { Int.MAX_VALUE }
        minDist[0] = 0
        var edgeUsed = 0
        while (edgeUsed < n) {
            var edgeWeight = Int.MAX_VALUE
            var node = -1
            for (i in points.indices) {
                if (!inMST[i] && minDist[i] < edgeWeight) {
                    edgeWeight = minDist[i]
                    node = i
                }
            }
            ret += edgeWeight
            edgeUsed++
            inMST[node] = true

            for (i in 0 until n) {
                val dst = Math.abs(points[i][0] - points[node][0]) + Math.abs(points[i][1] - points[node][1])
                if (!inMST[i] && minDist[i] > dst) {
                    minDist[i] = dst
                }
            }
        }
        return ret
    }
}