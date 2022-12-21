/**
 * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 */
class Solution_single_number {

    // Order doesn't matter for xor operation. And:
    // a xor a = 0
    // 0 xor n = n
    // So if we xor every elements in nums, it will be
    // (a xor a) xor (b xor b)... xor n = 0 xor 0... xor n = n
    // T:O(n) S:O(1)
    fun singleNumber(nums: IntArray): Int {
        return nums.reduce { a, b -> a xor b }
    }
}