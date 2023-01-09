/**
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 */
class Solution_longest_increasing_subsequence {

    // Using typical dynamic programming will end up with O(n^2),
    // since for each index we need to search all indexes before it.
    // Here is an optimal solution with building a sequence and keeping most possibility.
    // The idea is to try to store best sequence so far that opens most possibility for a new value.
    // Consider [8, 1, 6, 2, 3, 10], first we got [8], then we should replace it with 1, since 1 is smaller
    // and can accept more values as increasing order.
    // For larger sequences it is the same idea. Using binary search to improve replace efficiency.
    // T:O(nlogn) S:O(n)
    fun lengthOfLIS(nums: IntArray): Int {
        val tails = IntArray(nums.size)
        var size = 0
        for (x in nums) {
            // Find first element that is greater or equal to x using binary search
            var i = 0
            var j = size
            while (i != j) {
                val m = (j - i) / 2 + i
                if (tails[m] < x) {
                    i = m + 1
                } else {
                    j = m
                }
            }
            tails[i] = x
            if (i == size) size++
        }
        return size
    }
}