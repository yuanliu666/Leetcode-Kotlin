/**
 * Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
 * Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.
 *
 * Note:
 * A naive algorithm of O(n^2) is trivial. You MUST do better than that.
 *
 * Example:
 *
 * Input: nums = [-2,5,-1], lower = -2, upper = 2,
 * Output: 3
 * Explanation: The three ranges are : [0,0], [2,2], [0,2] and their respective sums are: -2, -1, 2.
 */

class Solution_count_of_range_sum {

    // Reference: https://leetcode.com/problems/count-of-range-sum/discuss/77990/Share-my-solution
    // When thinking problems about range sum, prefix sum is a really convenient way to get range sum.
    // Let sums[m] = sum of n[0, m), then S(i,j) = sums[j+1] - sums[i].
    // This solution uses merge sort on prefix sums, and count in the process.
    // T:O(nlogn) S:O(n)
    fun countRangeSum(nums: IntArray, lower: Int, upper: Int): Int {
        // sums could go overflow, use long instead
        val sums = LongArray(nums.size + 1, { 0 })
        for (i in 0 until nums.size) {
            sums[i + 1] = sums[i] + nums[i]
        }
        // nothing on nums now, all about prefix sums
        // it's important to understand that we are merge sorting prefix sums array, not nums array!
        return countAndMergeSort(sums, 0, sums.size, lower, upper)
    }

    // [start, end) matches sums
    private fun countAndMergeSort(sums: LongArray, start: Int, end: Int, lower: Int, upper: Int): Int {
        if (end - start <= 1) {
            return 0
        }
        val mid = start + (end - start).shr(1)
        // get left and right
        var count = countAndMergeSort(sums, start, mid, lower, upper) + countAndMergeSort(sums, mid, end, lower, upper)
        // get in between
        // here left and right half are already sorted (no guarantee right >= left tho)
        var j = mid
        var k = mid
        var r = mid
        val tmp = mutableListOf<Long>()
        // we are looking for ranges [i, h] where i in [start, mid) and h in [mid, end) that meets requirement
        // S(i,h)=sum[h+1]-sum[i], where h+1 in [mid+1, end]
        // but k could = mid here, why?
        // suppose k does not increment at all, then j will also not increase, we get j - k = 0 count
        // otherwise k++ and now k > mid satisfies this condition
        // it's a little bit tricky edge case but somehow handled very nicely
        for (i in start until mid) {
            // get lower and upper bound
            // k is inclusive lower bound (unless it does not increment at all), j is exclusive upper bound
            // so k, k+1, ..., j-1 are answers, namely S(i, k-1)..S(i, j-2), total j - k count
            // note that j,k,r are not reset during loop, this is because sums[i] are sorted in each half,
            // so when i grows, sum[i] cannot be smaller, we have:
            // sums[k] - sums[i'] <= sums[k] - sums[i] < lower, also
            // sums[j] - sums[i'] <= sums[j] - sums[i] <= upper
            // where i < i' < mid, so we don't need to repeat previous k and j
            while (k < end && sums[k] - sums[i] < lower) {
                k++
            }
            while (j < end && sums[j] - sums[i] <= upper) {
                j++
            }
            count += j - k
            // r and i acts as two pointers sort here
            // as a result tmp will holds sorted sums in the end
            while (r < end && sums[r] < sums[i]) {
                tmp.add(sums[r])
                r++
            }
            tmp.add(sums[i])
        }
        // write back
        for (i in 0 until tmp.size) {
            sums[start + i] = tmp[i]
        }
        return count
    }
}

fun main(args: Array<String>) {

}