/**
 * You are given an array trees where trees[i] = [xi, yi] represents the location of a tree in the garden.
 * You are asked to fence the entire garden using the minimum length of rope as it is expensive.
 * The garden is well fenced only if all the trees are enclosed.
 * Return the coordinates of trees that are exactly located on the fence perimeter.
 *
 * 1 <= points.length <= 3000
 * points[i].length == 2
 * 0 <= xi, yi <= 100
 * All the given points are unique.
 */
class Solution_erect_the_fence {
    /**
     * https://algorithmist.com/wiki/Monotone_chain_convex_hull

    Input: a list P of points in the plane.

    Sort the points of P by x-coordinate (in case of a tie, sort by y-coordinate).

    Initialize U and L as empty lists.
    The lists will hold the vertices of upper and lower hulls respectively.

    for i = 1, 2, ..., n:
    while L contains at least two points and the sequence of last two points
    of L and the point P[i] does not make a counter-clockwise turn:
    remove the last point from L
    append P[i] to L

    for i = n, n-1, ..., 1:
    while U contains at least two points and the sequence of last two points
    of U and the point P[i] does not make a counter-clockwise turn:
    remove the last point from U
    append P[i] to U

    Remove the last point of each list (it's the same as the first point of the other list).
    Concatenate L and U to obtain the convex hull of P.
    Points in the result will be listed in counter-clockwise order.
     */
    // This problem is hard and narrow, so don't spend too much time on it
    // T:O(nlogn) S:O(1)
    fun outerTrees(trees: Array<IntArray>): Array<IntArray> {
        // sort the point of P by x-coor (case tie, sort by y-coor)
        trees.sortWith(compareBy<IntArray> { it[0] }.thenBy { it[1] })
        // hold the vertices of upper and lower hulls
        val stk = Stack<IntArray>()
        // Build Lower layer of hulls
        for (i in trees.indices) {
            // sequence of last two points of Lower hulls
            // and the point P[i] does not make a counter-clockwise turn
            while (stk.size >= 2 && orientation(stk[stk.size - 2], stk[stk.size - 1], trees[i]) > 0) {
                stk.pop()
            }
            stk.push(trees[i])
        }
        stk.pop()
        // Build uper layer of hulls
        for (i in trees.indices.reversed()) {
            while (stk.size >= 2 && orientation(stk[stk.size - 2], stk[stk.size - 1], trees[i]) > 0) {
                stk.pop()
            }
            stk.push(trees[i])
        }
        // Remove last point of list (it's same as first point of  other list).
        // remove redundant elements from the stack
        val set = hashSetOf<IntArray>()
        while (stk.isNotEmpty()) {
            set.add(stk.pop())
        }
        return set.toArray(Array(set.size) { IntArray(2) })
    }

    //            q()
    //          /     \
    //        /         \
    //     p()            \
    //                     r()
    //
    // (q[1] - p[1]) / (q[0] - p[0])   vs  (r[1] - q[1]) / (r[0] - q[0])
    private fun orientation(p: IntArray, q: IntArray, r: IntArray): Int {
        return (q[1] - p[1]) * (r[0] - q[0]) - (q[0] - p[0]) * (r[1] - q[1])
    }
}