/**
 * You are given an array of integers nums, there is a sliding window of size k
 * which is moving from the very left of the array to the very right. You can only see the k numbers in the window.
 * Each time the sliding window moves right by one position.
 * Return the max sliding window.
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 */
class Solution_sliding_window_maximum {

    // Brute force is obviously O(nk).
    // Another way of thinking it is relating to min stack problem, here we are trying to implement a max queue.
    // Bascially, keep the queue values sorted, max to min. So max is the first element.
    // For update, poping out values that are smaller than new value and append new value. Why?
    // Because those smaller values are before new value,
    // when getting max they are "useless" since the new value will always win.
    // T:O(n) S:O(k)
    fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
        val n = nums.size
        if (k >= n) return intArrayOf(nums.max()!!) // Without '!!' LC will give error
        if (k == 1) return nums

        val result = IntArray(n - k + 1)
        val win: Deque<Int> = java.util.ArrayDeque() // Stores indices
        for (i in 0 until n) {
            // Remove first indice that is out of bound
            if (win.size > 0 && win.peekFirst() <= i - k) {
                win.pollFirst()
            }
            // Remove indices whose corresponding values are less than nums[i],
            // since they are before nums[i] and will be shadowed entirely by nums[i]
            while (win.size > 0 && nums[win.peekLast()] < nums[i]) {
                win.pollLast()
            }
            win.offerLast(i)
            // Add to result
            if (i >= k - 1) {
                result[i - k + 1] = nums[win.peekFirst()]
            }
        }
        return result
    }
}