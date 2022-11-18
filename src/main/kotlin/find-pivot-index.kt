/**
 * Given an array of integers nums, calculate the pivot index of this array.
 * The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to
 * the sum of all the numbers strictly to the index's right.
 * If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left.
 * This also applies to the right edge of the array.
 * Return the leftmost pivot index. If no such index exists, return -1.
 *
 * 1 <= nums.length <= 10^4
 * -1000 <= nums[i] <= 1000
 */

class Solution_find_pivot_index {

    // T:(n) S:O(1)
    fun pivotIndex(nums: IntArray): Int {
        var rightSum = nums.sum()
        var leftSum = 0
        for (i in nums.indices) {
            if (i - 1 >= 0) {
                leftSum += nums[i - 1]
            }
            rightSum -= nums[i]
            if (leftSum == rightSum) {
                return i
            }
        }
        return -1
    }

    // T:(n) S:O(n)
    fun pivotIndex2(nums: IntArray): Int {
        val rs = IntArray(nums.size)
        for (i in nums.indices) {
            rs[i] = nums[i] + if (i - 1 >= 0) rs[i - 1] else 0
        }
        for (i in nums.indices) {
            val sumLeft = if (i - 1 >= 0) rs[i - 1] else 0
            val sumRight = rs[nums.size - 1] - nums[i] - sumLeft
            if (sumLeft == sumRight) {
                return i
            }
        }
        return -1
    }
}