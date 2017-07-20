/**
 * There is a brick wall in front of you. The wall is rectangular and has several rows of bricks.
 * The bricks have the same height but different width. You want to draw a vertical line from
 * the top to the bottom and cross the least bricks.
 *
 * The brick wall is represented by a list of rows. Each row is a list of integers representing the
 * width of each brick in this row from left to right.
 *
 * If your line go through the edge of a brick, then the brick is not considered as crossed.
 * You need to find out how to draw the line to cross the least bricks and return the number of crossed bricks.
 *
 * You cannot draw a line just along one of the two vertical edges of the wall,
 * in which case the line will obviously cross no bricks.
 *
 * Example:
 * Input:
 * [[1,2,2,1],
 *  [3,1,2],
 *  [1,3,2],
 *  [2,4],
 *  [3,1,2],
 *  [1,3,1,1]]
 * Output: 2
 *
 * Note:
 * The width sum of bricks in different rows are the same and won't exceed INT_MAX.
 * The number of bricks in each row is in range [1,10,000].
 * The height of wall is in range [1,10,000].
 * Total number of bricks of the wall won't exceed 20,000.
 */

class Solution_brick_wall {
    // T:O(n*h) S:O(n) where n is number of bricks per height, h is the total height of wall
    fun getMinAcross(a: Array<Array<Int>>): Int {
        val map = mutableMapOf<Int, Int>()
        val totalWidth = a[0].sum()
        var width: Int
        for (row in a) {
            width = 0
            for (w in row)
            // right margin does not count
                if (width + w != totalWidth) {
                    width += w
                    map.putIfAbsent(width, 0)
                    map.put(width, map[width]!!.inc())
                }
        }
        // in case all one brick per height
        if (map.isEmpty()) {
            return a.size
        } else {
            return a.size - map.values.max() as Int
        }
    }
}

fun main(args: Array<String>) {
    val s = Solution_brick_wall()
    println(s.getMinAcross(arrayOf(
            arrayOf(1, 2, 2, 1),
            arrayOf(3, 1, 2),
            arrayOf(1, 3, 2),
            arrayOf(2, 4),
            arrayOf(3, 1, 2),
            arrayOf(1, 3, 1, 1)
    )))

    println(s.getMinAcross(arrayOf(
            arrayOf(2),
            arrayOf(2)
    )))

    println(s.getMinAcross(arrayOf(
            arrayOf(1, 1),
            arrayOf(2)
    )))
}