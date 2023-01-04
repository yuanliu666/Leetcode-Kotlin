/**
 * Given an array of integers temperatures represents the daily temperatures,
 * return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature.
 * If there is no future day for which this is possible, keep answer[i] == 0 instead.
 */
class Solution_daily_temperatures {

    // Monotonic stack solution.
    // T:O(n) as each element can go into stack once, S:O(n).
    fun dailyTemperatures(temperatures: IntArray): IntArray {
        // Stack for indices
        val stk = Stack<Int>()
        val n = temperatures.size
        val ret = IntArray(n)
        ret[n - 1] = 0
        stk.push(n - 1)
        for (i in (0 until n - 1).reversed()) {
            while (stk.isNotEmpty() && temperatures[stk.peek()] <= temperatures[i]) {
                stk.pop()
            }
            ret[i] = if (stk.isEmpty()) {
                0
            } else {
                stk.peek() - i
            }
            stk.push(i)
        }
        return ret
    }

    // Constant space solution.
    // The trick is to use result array to help searching warmer day.
    // From right to left, assuming right potion is already solved, for the new day A,
    // we look at it next day, B, to check if it is warmer, if it is then problem solved,
    // otherwise we do a "jump" to B's warmer day C, since we know that the days between B and C are not warmer for B,
    // and B is not warmer for A. And so forth until we find next warmer day.
    // But what there is no warmer day for A? Therefore we need to keep track of hottest day on right and handle it.
    // T:O(n) S:O(1)
    fun dailyTemperatures2(temperatures: IntArray): IntArray {
        val n = temperatures.size
        val ret = IntArray(n)
        var hottest = temperatures[n - 1]
        ret[n - 1] = 0
        for (i in (0 until n - 1).reversed()) {
            if (temperatures[i] >= hottest) {
                ret[i] = 0
                hottest = temperatures[i]
                continue
            }
            var day = 1
            while (temperatures[i] >= temperatures[i + day]) {
                day += ret[i + day]
            }
            ret[i] = day
        }
        return ret
    }
}