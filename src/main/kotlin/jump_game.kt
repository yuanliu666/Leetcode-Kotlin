/**
 * You are given an integer array nums. You are initially positioned at the array's first index,
 * and each element in the array represents your maximum jump length at that position.
 * Return true if you can reach the last index, or false otherwise.
 */
class Solution_jump_game {

    // Mimick jump process. At each step trying to find max reach and corresponding point.
    // Once cannot move forward further, it means process got stuck and will not reach end.
    // T:O(n) S:O(1)
    fun canJump(nums: IntArray): Boolean {
        if (nums.size == 1) return true
        var cur = 0
        var reach = nums[0]
        while (true) {
            if (reach <= cur) return false
            var nextReach = cur
            for (i in cur + 1..reach) {
                if (i + nums[i] > nextReach) {
                    nextReach = i + nums[i]
                    if (nextReach >= nums.size - 1) return true
                    cur = i
                }
            }
            reach = nextReach
        }
    }

    // We only need one way to jump to last index. From end to start, we check for left-most valid jump point,
    // and finally check if the path reaches out to 0. If it does, then this path will work in reverse order.
    // If not, it means from index 0 cannot reach those jump points, and those jump points are already most viable since
    // we override with left most positions.
    // T:O(n) S:O(1)
    fun canJump2(nums: IntArray): Boolean {
        var lastPos = nums.size - 1
        for (i in nums.indices.reversed()) {
            if (i + nums[i] >= lastPos) {
                lastPos = i
            }
        }
        return lastPos == 0
    }
}