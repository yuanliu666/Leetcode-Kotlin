/**
 * You have k lists of sorted integers in non-decreasing order.
 * Find the smallest range that includes at least one number from each of the k lists.
 * We define the range [a, b] is smaller than range [c, d] if b - a < d - c or a < c if b - a == d - c.
 */
class Solution_smallest_range_covering_elements_from_k_lists {

    // The algorithm is to keep a list of smaller elements from each list,
    // and the min-max of this list will cover all lists since those elements are from the lists.
    // And then increase min by looking up next min across lists and replace previous element from the same list.
    // T:O(nlogm), n is total number of elements inside numbers, m is number of list in nums. S:O(m)
    fun smallestRange(nums: List<List<Int>>): IntArray {
        // Record min range start and end
        var minStart = 0
        var minEnd = Int.MAX_VALUE
        // Record max value so far
        var maxValue = Int.MIN_VALUE
        // Array of next indexes for each list in nums
        val next = IntArray(nums.size)
        // Min heap based on value of pointed index in the list
        // Basically, each list will have 1 value inside heap
        val heap = PriorityQueue<Int>(compareBy { nums[it][next[it]] })
        for (i in nums.indices) {
            heap.offer(i)
            maxValue = kotlin.math.max(maxValue, nums[i][next[i]])
        }
        // i and j are to count all numbers inside num, they are not used otherwise
        for (i in nums.indices) {
            for (j in nums[i].indices) {
                // Find current min value. The range of min-max will cover all lists since every list has an element here
                val minListIndex = heap.poll()
                // Update result if it is smaller
                if (maxValue - nums[minListIndex][next[minListIndex]] < minEnd - minStart) {
                    minStart = nums[minListIndex][next[minListIndex]]
                    minEnd = maxValue
                }
                // Point to next value in list
                next[minListIndex]++
                // If list is done, then it's done since moving further will lose coverage for this list
                if (next[minListIndex] == nums[minListIndex].size) {
                    return intArrayOf(minStart, minEnd)
                }
                // Refill heap
                heap.offer(minListIndex)
                // Update max value so far
                maxValue = kotlin.math.max(maxValue, nums[minListIndex][next[minListIndex]])
            }
        }
        return intArrayOf(minStart, minEnd)
    }
}