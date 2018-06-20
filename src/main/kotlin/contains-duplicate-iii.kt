/**
 * Given an array of integers, find out whether there are two distinct indices i and j in the array
 * such that the absolute difference between nums\[i] and nums\[j] is at most t
 * and the absolute difference between i and j is at most k.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1], k = 3, t = 0
 * Output: true
 *
 * Example 2:
 *
 * Input: nums = [1,0,1,1], k = 1, t = 2
 * Output: true
 *
 * Example 3:
 *
 * Input: nums = [1,5,9,1,5,9], k = 2, t = 3
 * Output: false
 */

class Solution_contains_duplicate_iii {

    // T:O(n) S:O(k)
    fun containsNearbyAlmostDuplicate(nums: IntArray, k: Int, t: Int): Boolean {
        if (k < 0 || t < 0) {
            return false
        }
        // window with max size of k, not k + 1 because of adding & removing happens after checking
        val window = object : LinkedHashMap<Int, Int>() {
            override fun removeEldestEntry(eldest: MutableMap.MutableEntry<Int, Int>?): Boolean {
                return size > k
            }
        }
        for (n in nums) {
            val bucket: Int = if (t == 0) {
                n
            } else {
                n / t
            }
            for (j in (bucket - 1)..(bucket + 1)) {
                // prevent overflow
                if (window.containsKey(j) && compareAbsAndPreventOverflow(n, window[j] ?: 0, t)) {
                    return true
                }
            }
            window[bucket] = n
        }
        return false
    }

    private fun compareAbsAndPreventOverflow(a: Int, b: Int, t: Int): Boolean = java.lang.Math.abs(a.toLong() - b.toLong()) <= t.toLong()
}

fun main(args: Array<String>) {
    println(Solution_contains_duplicate_iii().containsNearbyAlmostDuplicate(intArrayOf(-1, 2147463647), 1, 2147463647))
}