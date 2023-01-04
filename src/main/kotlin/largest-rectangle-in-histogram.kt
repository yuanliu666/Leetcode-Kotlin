/**
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
 * return the area of the largest rectangle in the histogram.
 */
class Solution_largest_rectangle_in_histogram {

    // Monotonic stack solution.
    // Keep an increasing stack. When another larger value comes, put in.
    // Otherwise, start poping until previous value is smaller.
    // We can see last indice i - 1 is always in when considering i.
    // An important observation is, for two adjacent indices i and j, h[i] < h[j], what's in between?
    // The answer h[k] >= h[j] for i< k <j. Why?
    // Because if h[k] < h[j], it should stay in the stack.
    // e.g. h[i] = 50, h[j] = 100, if the values in between are something like 30, it will kick out 50;
    // if they are 70, they will stay and 100 won't kick them out. So they should be something like 101,
    // which won't kick out 50 and kicked out by 100.
    // Then, for each pop up value, try to calculate the area with its height.
    // Let's say current index is i, pop up value is j, and on top of stack is k.
    // Then we have h[i] <= h[j] and h[j] > h[k], so considering the rectangle formed from k + 1 to j,
    // since we keep an increasing stack, the values between k and j are larger than h[j],
    // So the area = h[j] * (i - k - 1)
    // Need to handle empty stack situation. It means the value is smallest so far and count full width till i.
    // Also we need do calculation at the end for the increasing heights in stack.
    // T:O(n) as element can go in stack at most once; S:O(n)
    fun largestRectangleArea(heights: IntArray): Int {
        val n = heights.size
        val stk = Stack<Int>()
        var maxArea = 0
        var i = 0
        while (i <= n) {
            if (stk.isEmpty() || (i < n && heights[i] > heights[stk.peek()])) {
                stk.push(i)
                i++
            } else {
                val last = stk.pop()
                maxArea = if (stk.isEmpty()) {
                    kotlin.math.max(maxArea, heights[last] * i)
                } else {
                    kotlin.math.max(maxArea, heights[last] * (i - stk.peek() - 1))
                }
            }
        }
        return maxArea
    }
}