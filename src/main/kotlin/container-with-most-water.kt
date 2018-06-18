/**
 * Given n non-negative integers a1, a2, ..., an,
 * where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 *
 * Note: You may not slant the container and n is at least 2.
 */

class Solution_container_with_most_water {

    // T:O(n) S:O(1)
    fun maxArea(height: IntArray): Int {
        var ret = 0
        var i = 0
        var j = height.lastIndex
        while (i < j) {
            ret = java.lang.Math.max(ret, (j - i) * java.lang.Math.min(height[i], height[j]))
            if (height[i] < height[j]) {
                i++
            } else {
                j--
            }
        }
        return ret
    }
}

fun main(args: Array<String>) {
    // LC OJ Passed
    // See [ContainerWithMostWaterTest] for unit test
}