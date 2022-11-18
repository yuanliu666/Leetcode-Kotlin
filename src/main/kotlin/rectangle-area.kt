import kotlin.math.max
import kotlin.math.min

/**
 * Given the coordinates of two rectilinear rectangles in a 2D plane, return the total area covered by the two rectangles.
 * The first rectangle is defined by its bottom-left corner (ax1, ay1) and its top-right corner (ax2, ay2).
 * The second rectangle is defined by its bottom-left corner (bx1, by1) and its top-right corner (bx2, by2).
 *
 * -10^4 <= ax1 <= ax2 <= 10^4
 * -10^4 <= ay1 <= ay2 <= 10^4
 * -10^4 <= bx1 <= bx2 <= 10^4
 * -10^4 <= by1 <= by2 <= 10^4
 */
class Solution_rectangle_area {

    // T:O(1) S:O(1)
    fun computeArea(ax1: Int, ay1: Int, ax2: Int, ay2: Int, bx1: Int, by1: Int, bx2: Int, by2: Int): Int {
        val w1 = ax2 - ax1
        val h1 = ay2 - ay1
        val w2 = bx2 - bx1
        val h2 = by2 - by1
        // w3 and h3 represents width and height of the smallest rectangle covers both rectangles
        val w3 = max(ax2, bx2) - min(ax1, bx1)
        val h3 = max(by2, ay2) - min(ay1, by1)
        // We can know if there is overlap by checking sign of those
        val w4 = w1 + w2 - w3
        val h4 = h1 + h2 - h3
        return if (w4 < 0 || h4 < 0) {
            // need both overlap to have overlap area
            w1 * h1 + w2 * h2
        } else {
            w1 * h1 + w2 * h2 - w4 * h4
        }
    }
}