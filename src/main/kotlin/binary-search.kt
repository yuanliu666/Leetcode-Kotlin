/**
 * Given an array of integers nums which is sorted in ascending order, and an integer target,
 * write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.
 * You must write an algorithm with O(log n) runtime complexity.
 */
class Solution_binary_search {

    // T:O(logn) S:O(1)
    fun search(nums: IntArray, target: Int): Int {
        var lo = 0
        var hi = nums.size - 1
        while (lo <= hi) {
            val mid = (lo + hi) / 2
            if (nums[mid] == target) {
                return mid
            } else if (nums[mid] > target) {
                hi = mid - 1
            } else {
                lo = mid + 1
            }
        }
        return -1
    }
}