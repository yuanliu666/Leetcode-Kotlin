/**
 * There is an integer array nums sorted in ascending order (with distinct values).
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length)
 * such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 * For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 * Given the array nums after the possible rotation and an integer target,
 * return the index of target if it is in nums, or -1 if it is not in nums.
 * You must write an algorithm with O(log n) runtime complexity.
 */
class Solution_search_in_rotated_sorted_array {

    // If rotated, we can draw the shape: two parts, each part is ascending,
    // but second part will be smaller than all of first part.
    // This way, we can first check head and tail to see if this array is actually roteated, if not, just do normal binary search.
    // If it is, then we need to handle some cases:
    // 1. mid can be at left or right part. How do we know? By comparing the value to head or tail.
    // 2. t can aslo be at left or right, and we can use the same way.
    // 3. Once we know where are mid and t located, we can know how to shrink the range.
    // Drawing a draft will help understand this problem quite a lot.
    // T:O(logn) S:O(1)
    fun search(nums: IntArray, target: Int): Int {
        val rotated = nums[0] > nums[nums.size - 1]
        if (!rotated) {
            return binarySearch(nums, target)
        }
        var lo = 0
        var hi = nums.size - 1
        while (lo < hi) {
            val m = (hi - lo) / 2 + lo
            when {
                nums[m] == target -> return m
                nums[m] > target -> {
                    if (nums[m] > nums[nums.size - 1]) {    // m on first part
                        if (target > nums[nums.size - 1]) { // t on first part as well
                            hi = m - 1
                        } else {
                            lo = m + 1
                        }
                    } else {    // m on second part, only left has smaller values
                        hi = m - 1
                    }
                }

                nums[m] < target -> {
                    if (nums[m] > nums[nums.size - 1]) {
                        lo = m + 1
                    } else {    // m on second part
                        if (target > nums[nums.size - 1]) {
                            hi = m - 1
                        } else {
                            lo = m + 1
                        }
                    }
                }
            }
        }
        if (nums[lo] == target) return lo
        return -1
    }

    private fun binarySearch(nums: IntArray, t: Int): Int {
        var lo = 0
        var hi = nums.size - 1
        while (lo < hi) {
            val m = (hi - lo) / 2 + lo
            when {
                nums[m] == t -> return m
                nums[m] > t -> hi = m - 1
                else -> lo = m + 1
            }
        }
        if (nums[lo] == t) return lo
        return -1
    }
}