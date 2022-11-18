/**
 * Our hero Teemo is attacking an enemy Ashe with poison attacks! When Teemo attacks Ashe, Ashe gets poisoned for an exact duration seconds.
 * More formally, an attack at second t will mean Ashe is poisoned during the inclusive time interval [t, t + duration - 1].
 * If Teemo attacks again before the poison effect ends, the timer for it is reset,
 * and the poison effect will end duration seconds after the new attack.
 * You are given a non-decreasing integer array timeSeries, where timeSeries[i]
 * denotes that Teemo attacks Ashe at second timeSeries[i], and an integer duration.
 * Return the total number of seconds that Ashe is poisoned.
 *
 * 1 <= timeSeries.length <= 10^4
 * 0 <= timeSeries[i], duration <= 10^7
 * timeSeries is sorted in non-decreasing order.
 */

class Solution_teemo_attacking {

    // T:O(n) S:O(1)
    fun findPoisonedDuration(timeSeries: IntArray, duration: Int): Int {
        if (duration == 0) return 0
        var result = 0
        var s = 0
        var e = 0
        for (n in timeSeries) {
            if (n >= e) {
                result += (e - s)
                s = n
            }
            e = n + duration
        }
        result += (e - s)
        return result
    }
}