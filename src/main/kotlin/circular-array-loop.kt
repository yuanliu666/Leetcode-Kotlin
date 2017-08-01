/**
 * You are given an array of positive and negative integers.
 * If a number n at an index is positive, then move forward n steps.
 * Conversely, if it's negative (-n), move backward n steps.
 * Assume the first element of the array is forward next to the last element,
 * and the last element is backward next to the first element.
 * Determine if there is a loop in this array.
 * A loop starts and ends at a particular index with more than 1 element along the loop.
 * The loop must be "forward" or "backward'.
 *
 * Example 1: Given the array [2, -1, 1, 2, 2], there is a loop, from index 0 -> 2 -> 3 -> 0.
 *
 * Example 2: Given the array [-1, 2], there is no loop.
 *
 * Note: The given array is guaranteed to contain no element "0".
 *
 * Can you do it in O(n) time complexity and O(1) space complexity?
 */

class Solution_circular_array_loop {
    // For a certain number, its following path is determined
    // So if we can store the result, we can achieve O(n) time complexity
    // The difficulty is how to achieve O(1) space
    // Recall the double pointers for finding a loop in linked list, we can also use here
    // How to check forward or backward? Use multiply sign
    // Also careful about single element loop, which does not count in this problem
    // Then instead of storing results, we just set 0 for impossible elements

    // T:O(n) S:O(1)
    // the loops mentioned here are the loops defined in the problem, not normal loops
    fun hasLoop(a: MutableList<Int>): Boolean {

        for (i in 0..a.size - 1) {
            if (a[i] == 0) {
                continue
            }
            // double pointers, slow goes one step at a time while fast goes two steps at a time
            var slow = i
            var fast = i
            // direction must be the same to form loops
            while (a[getNextIndex(a, slow)] * a[i] > 0
                    && a[getNextIndex(a, fast)] * a[i] > 0
                    && a[getNextIndex(a, getNextIndex(a, fast))] * a[i] > 0) {
                slow = getNextIndex(a, slow)
                fast = getNextIndex(a, getNextIndex(a, fast))
                // avoid single element false loop
                if (slow == fast) {
                    if (slow == getNextIndex(a, slow)) {
                        break
                    }
                    return true
                }
            }
            // loop not found, mark the path in same direction as not possible by setting to 0s
            // because even there are other elements with same direction come into this path, cannot form a loop
            // it is still undetermined about the reverse direction part, so do not modify them
            slow = i
            val v = a[i]
            while (a[slow] * v > 0) {
                val temp = getNextIndex(a, slow)
                a[slow] = 0
                slow = temp
            }
        }
        return false
    }

    fun getNextIndex(a: MutableList<Int>, cur: Int): Int {
        // use floor mod here otherwise will produce negative numbers
        return Math.floorMod(cur + a[cur], a.size)
    }
}

fun main(args: Array<String>) {
    val s = Solution_circular_array_loop()
    println(s.hasLoop(mutableListOf(2, -1, 1, 2, 2)))
    println(s.hasLoop(mutableListOf(-1, 2)))
}