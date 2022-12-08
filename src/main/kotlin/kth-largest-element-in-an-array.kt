/**
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * You must solve it in O(n) time complexity.
 */
class Solution_kth_largest_element_in_an_array {

    // Quick select algorithm.
    // T:O(n) average, O(n^2) worst case, S:O(1).
    // Convert the problem to find nth smallest, n = size + 1 - k
    // If we select a pivot, put everything smaller on left and larger on right,
    // if left size == n - 1, we know the pivot is nth smallest;
    // if left size > n -1, the nth smallest is at left;
    // otherwise, search right.
    // We can also use random pivot, or shuffle to try to reduce worst case posibility.
    fun findKthLargest(nums: IntArray, K: Int): Int {
        var k = K
        k = nums.size - k
        var lo = 0
        var hi = nums.size - 1
        while (lo < hi) {
            val j = partition(nums, lo, hi)
            if (j < k) {
                lo = j + 1
            } else if (j > k) {
                hi = j - 1
            } else break
        }
        return nums[k]
    }

    // Partition based on a[lo]
    private fun partition(a: IntArray, lo: Int, hi: Int): Int {
        var i = lo
        var j = hi + 1
        while (true) {
            do {
                i++
            } while (i < hi && a[i] < a[lo])
            do {
                j--
            } while (j > lo && a[lo] < a[j])
            if (i >= j) break
            swap(a, i, j)
        }
        swap(a, lo, j)
        return j
    }

    private fun swap(a: IntArray, i: Int, j: Int) {
        val tmp = a[i]
        a[i] = a[j]
        a[j] = tmp
    }
}