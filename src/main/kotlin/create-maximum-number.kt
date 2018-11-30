/**
 * Given two arrays of length m and n with digits 0-9 representing two numbers.
 * Create the maximum number of length k <= m + n from digits of the two.
 * The relative order of the digits from the same array must be preserved.
 * Return an array of the k digits.
 *
 * Note: You should try to optimize your time and space complexity.
 *
 * Example 1:
 * Input:
 * nums1 = [3, 4, 6, 5]
 * nums2 = [9, 1, 2, 5, 8, 3]
 * k = 5
 * Output:
 * [9, 8, 6, 5, 3]
 *
 * Example 2:
 * Input:
 * nums1 = [6, 7]
 * nums2 = [6, 0, 4]
 * k = 5
 * Output:
 * [6, 7, 6, 0, 4]
 *
 * Example 3:
 * Input:
 * nums1 = [3, 9]
 * nums2 = [8, 9]
 * k = 3
 * Output:
 * [9, 8, 9]
 */

class Solution_create_maximum_number {

    // Initial thought is greedy: choose largest from both array per step and recur rest.
    // But the problem is when they are equal. Then we need to go a step further and try both.
    // As an extreme case of all numbers are equal, this method will have O(2^n) complexity, which is not ideal.
    // From another aspect, we can break k into i + (k - i).
    // Namely we select i max number from nums1 and (k - 1) max number from nums2.
    // Still we need to figure out how to merge two max array,
    // and then iterate i to pick the final largest result.

    // T:O(k*(m+n+m*n))
    // S:O(m + n + k)
    fun maxNumber(nums1: IntArray, nums2: IntArray, k: Int): IntArray {
        val m = nums1.size
        val n = nums2.size
        // it is possible that, k is so large hence some numbers must be fetched from nums1
        val lo = java.lang.Math.max(0, k - n)
        // cannot exceed array size or k
        val hi = java.lang.Math.min(m, k)
        var ret: MutableList<Int> = mutableListOf()
        for (i in lo until hi + 1) {
            val candidate = merge(getMaxFromArray(nums1, i), getMaxFromArray(nums2, k - i))
            if (ret.isEmpty() || isGreater(candidate, 0, ret, 0)) {
                ret = candidate.toMutableList()
            }
        }
        return ret.toIntArray()
    }

    // create a max k-digit number from nums array, assume nums.size >= k
    // T: O(n) S:O(n)
    private fun getMaxFromArray(nums: IntArray, k: Int): List<Int> {
        val stk = java.util.Stack<Int>()
        for (i in 0 until nums.size) {
            while (stk.isNotEmpty()
                    && stk.peek() < nums[i]
                    // make sure there are enough numbers left
                    && stk.size + nums.size - i - 1 >= k) {
                stk.pop()
            }
            if (stk.size < k) {
                stk.push(nums[i])
            }
        }
        return stk.toList()
    }

    // Compare 2 int list. Return true if nums1 > nums2.
    // T:O(min(m, n)) S:O(1)
    private fun isGreater(nums1: List<Int>, i: Int, nums2: List<Int>, j: Int): Boolean {
        var i_ = i
        var j_ = j
        while (i_ < nums1.size
                && j_ < nums2.size
                && nums1[i_] == nums2[j_]) {
            i_++
            j_++
        }
        return j_ == nums2.size || (i_ < nums1.size && nums1[i_] > nums2[j_])
    }

    // Now we have 2 array of total size k, we need to merge them in correct order to produce largest result.
    // What if at some point the values equal?
    // We need to continue for comparision until they are not equal.
    // In this sense, its time complexity is O(mn). S:O(k)
    private fun merge(nums1: List<Int>, nums2: List<Int>): List<Int> {
        val ret = mutableListOf<Int>()
        var i = 0
        var j = 0
        for (r in 0 until nums1.size + nums2.size) {
            if (isGreater(nums1, i, nums2, j)) {
                ret.add(nums1[i])
                i++
            } else {
                ret.add(nums2[j])
                j++
            }
        }
        return ret
    }
}

fun main(args: Array<String>) {

}