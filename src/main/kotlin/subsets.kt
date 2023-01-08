/**
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 * Constraints:
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * All the numbers of nums are unique.
 */
class Solution_subsets {

    // Bit mask solution. Consider n bits, 0 on ith bit means nums[i] is absent, 1 means present.
    // So the int presentation of 0 ~ 2^n - 1 can be translated into lists and will be the result.
    // Since there are at most 10 bits, it's essentially O(1) for each operation,
    // T:O(2^n), S:O(n) if only consider the buffer.
    fun subsets(nums: IntArray): List<List<Int>> {
        val n = nums.size
        if (n == 0) return listOf(emptyList())
        val ret = mutableListOf<MutableList<Int>>()
        for (i in 0 until 2.pow(n)) {
            val temp = mutableListOf<Int>()
            var mask = 2.pow(n - 1)
            var j = 0
            while (mask > 0) {
                if (mask and i > 0) {
                    temp.add(nums[j])
                }
                mask = mask ushr 1
                j++
            }
            ret.add(temp)
        }
        return ret
    }

    private fun Int.pow(n: Int): Int {
        return Math.pow(this.toDouble(), n.toDouble()).toInt()
    }
}