/**
 * Given an integer array nums, return the maximum result of nums[i] XOR nums[j], where 0 <= i <= j < n.
 *
 * 1 <= nums.length <= 2 * 10^5
 * 0 <= nums[i] <= 2^31 - 1
 */
class Solution_maximum_xor_of_two_numbers_in_an_array {

    // The idea is to first check from highest bit to see if we can make it a 1
    // For example, let's say we first check 0b100, we need a mask since right now we only care about this bit
    // Then we just need to check if there are both 0 and 1 for this bit
    // What about next bit 0b10? If we need to track numbers with previous 0 and 1, it can be quite complex
    // But we don't have to, since we already know if 0b100 is achieveable
    // Let's say it is, so for this case, we try to check if there is 0b110 as a result
    // Now we get first 2 bits of each number, how do we know if they can xor to max result?
    // The trick is: if a xor b = c, then a xor c = b
    // So use each number to xor target, if the result in the set, then we can find the pair
    // And do the same for remaining bits
    // T:O(n) S:O(n)
    fun findMaximumXOR(nums: IntArray): Int {
        // record current max number from left bits
        var maxNum = 0
        var mask = 0
        for (i in (0 until 32).reversed()) {
            mask = mask or (1 shl i)
            // max number we can get as a target
            val target = maxNum or (1 shl i)
            val set = hashSetOf<Int>()
            for (n in nums) {
                // we only care about left bits
                set.add(n and mask)
            }
            for (e in set) {
                // if a xor b = c, then a xor c = b. Check if set can achieve target
                if (set.contains(e xor target)) {
                    maxNum = target
                    break
                }
            }
        }
        return maxNum
    }
}