/**
 * You are given an integer array matchsticks where matchsticks[i] is the length of the ith matchstick.
 * You want to use all the matchsticks to make one square.
 * You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.
 * Return true if you can make this square and false otherwise.
 *
 * Constraints:
 * 1 <= matchsticks.length <= 15
 * 1 <= matchsticks[i] <= 10^8
 */
class Solution {

    // https://leetcode.com/problems/matchsticks-to-square/solutions/164059/matchsticks-to-square/
    // Some observations:
    // - Since every match has to be used, the sum should be multiples of 4, sum = w * 4 where w is width
    // - Since cannot break match, should have max(match) <= w
    // - If we can somehow form 3 sides of width, the remaining is fixed, so we just need 3 instead of 4
    // - Since matches size <= 15, we can use a bit mask instead of boolean array
    // The idea is near brute force, using visited mask and backtracking to iterate through matches, but will get TLE.
    // To improve efficiency, we use a memo map to help store solved sub-problems.
    // T:O(n*2^n) S:O(n*2^n)
    fun makesquare(matchsticks: IntArray): Boolean {
        val map = HashMap<Pair<Int, Int>, Boolean>()
        var perimeter = 0
        var max = Int.MIN_VALUE
        for (n in matchsticks) {
            perimeter += n
            max = kotlin.math.max(max, n)
        }

        if (perimeter % 4 != 0 || max > perimeter / 4) {
            return false
        }

        return findByBackTracking(
            matchsticks,
            perimeter / 4,
            (1 shl matchsticks.size) - 1,
            0,
            0,
            map
        )
    }

    private fun findByBackTracking(
        matchsticks: IntArray,
        sideLen: Int,
        mask: Int,
        sidesDone: Int,
        currentPerimeter: Int,
        cache: HashMap<Pair<Int, Int>, Boolean>
    ): Boolean {
        val key = Pair(mask, sidesDone)

        cache[key]?.let {
            return it
        }

        val newSidesDone = if (currentPerimeter == sideLen) {
            sidesDone + 1
        } else {
            sidesDone
        }

        if (currentPerimeter == sideLen && newSidesDone == 3) {
            // three sides formed
            return true
        }

        val rem = when {
            currentPerimeter % sideLen == 0 -> {
                sideLen
            }

            else -> {
                sideLen - currentPerimeter
            }
        }

        matchsticks.forEachIndexed { index, value ->
            if (mask and (1 shl index) != 0 && value <= rem) {
                if (
                    findByBackTracking(
                        matchsticks,
                        sideLen,
                        mask xor (1 shl index),
                        newSidesDone,
                        if (currentPerimeter == sideLen) value else currentPerimeter + value,
                        cache
                    )
                ) {
                    return true
                }
            }
        }

        cache[key] = false
        return false
    }
}