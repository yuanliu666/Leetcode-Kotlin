/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it can trap after raining.
 */
class Solution_trapping_rain_water {

    // Oberservation is that we can compute for each index, where water=min(leftMax,rightMax)-h
    // So initial thought is get max left and max right, and compute.
    // 3 scans. T:O(n) S:O(n)
    fun trap(height: IntArray): Int {
        val n = height.size
        val maxLeft = IntArray(n)
        val maxRight = IntArray(n)
        var max = 0
        for (i in height.indices) {
            maxLeft[i] = max
            max = kotlin.math.max(max, height[i])
        }
        max = 0
        for (i in height.indices.reversed()) {
            maxRight[i] = max
            max = kotlin.math.max(max, height[i])
        }
        var ret = 0
        for (i in height.indices) {
            if (maxLeft[i] <= height[i] || maxRight[i] <= height[i]) continue
            ret += kotlin.math.min(maxLeft[i], maxRight[i]) - height[i]
        }
        return ret
    }

    // An improved solution is 2 scans, put left max computation and result computation together.
    fun trap2(height: IntArray): Int {
        val n = height.size
        val maxLeft = IntArray(n)
        val maxRight = IntArray(n)
        var max = 0
        for (i in height.indices.reversed()) {
            maxRight[i] = max
            max = kotlin.math.max(max, height[i])
        }
        max = 0
        var ret = 0
        for (i in height.indices) {
            maxLeft[i] = max
            max = kotlin.math.max(max, height[i])
            if (maxLeft[i] <= height[i] || maxRight[i] <= height[i]) continue
            ret += kotlin.math.min(maxLeft[i], maxRight[i]) - height[i]
        }
        return ret
    }

    // Optimized solution is 1 scan and no extra space, using two pointers.
    // So if we cacluate potions of left and right, for the indices in left we already have their maxLeft,
    // for indices in right we have maxRight, but what about maxRight for left and maxLeft for right?
    // The trick is, we only need to know min of maxLeft and maxRight.
    // That is to say, if we can let maxRight for left >= maxLeft, we don't need know what exactly maxRight is.
    // In order to achieve this, we move smaller of left and right pointer.
    // T:O(n) S:O(1)
    fun trap3(height: IntArray): Int {
        // using pointers
        if (height.size < 2) return 0
        var amount = 0
        var left = 0
        var right = height.size - 1
        var maxLeft = height[left]
        var maxRight = height[right]

        while (left < right) {
            if (maxLeft <= maxRight) {
                // shift left ptr to right
                left++
                maxLeft = maxOf(maxLeft, height[left])
                // maxLeft = max(height[left], min(maxLeft, maxRight))
                amount += maxLeft - height[left]
            } else {
                // shift right ptr to left
                right--
                maxRight = maxOf(maxRight, height[right])
                amount += maxRight - height[right]
            }
        }
        return amount;
    }
}