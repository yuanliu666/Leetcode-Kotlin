/**
 * A group of two or more people wants to meet and minimize the total travel distance.
 * You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group.
 * The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 *
 * For example, given three people living at (0,0), (0,4), and (2,2):
 *
 * 1 - 0 - 0 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 * The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.
 *
 * Hint:
 *
 * Try to solve it in one dimension first. How can this solution apply to the two dimension case?
 */

// Apparently the point has (x, y) of the median value of all xs and all ys.
// To get median value, we can traverse once and sort, which is O(m*n + nlogn + mlogm)
// Or we can traverse two times to get x and y separately and keep it sorted from start (used in following code)
// Or we can use some other O(n) algorithm to find median in array, but it's more complicated

// To calculate, of course use median is a way to do, but actually if list is sorted,
// we can just start from both front and end and accumulate the difference
// That is because we can always pair other elements except median, x2 - median + median - x1 = x2 - x1

// T:O(m*n) S:O(m+n)
class Solution_best_meeting_point {
    fun getMinDistance(g: Array<Array<Int>>): Int {
        val xArr = mutableListOf<Int>()
        val yArr = mutableListOf<Int>()
        // get x, x is ensured to be ascending
        for (i in 0..g.size - 1)
            for (j in 0..g[0].size - 1)
                if (g[i][j] == 1)
                    xArr.add(i)
        // get y
        for (j in 0..g[0].size - 1)
            for (i in 0..g.size - 1)
                if (g[i][j] == 1)
                    yArr.add(j)
        return getDiffSum(xArr) + getDiffSum(yArr)
    }

    fun getDiffSum(a: List<Int>): Int {
        var i = 0
        var j = a.size - 1
        var ret = 0
        while (i < j) {
            ret += a[j] - a[i]
            i++
            j--
        }
        return ret
    }
}

fun main(args: Array<String>) {
    val s = Solution_best_meeting_point()
    println(s.getMinDistance(arrayOf(
            arrayOf(1, 0, 0, 0, 1),
            arrayOf(0, 0, 0, 0, 0),
            arrayOf(0, 0, 1, 0, 0))))
}
