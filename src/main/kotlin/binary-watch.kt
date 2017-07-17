/**
 * A binary watch has 4 LEDs on the top which represent the hours (0-11),
 * and the 6 LEDs on the bottom represent the minutes (0-59).
 *
 * Each LED represents a zero or one, with the least significant bit on the right.
 *
 * For example, the above binary watch reads "3:25".
 *
 * Given a non-negative integer n which represents the number of LEDs that are currently on,
 * return all possible times the watch could represent.
 *
 * Example:
 *
 * Input: n = 1
 * Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 * Note:
 * The order of output does not matter.
 * The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
 */

class Solution_binary_watch {
    // 4 LED means 0-15, while hours can be 0-11, so 0-3 possible LEDs for hours
    // similarly 0-4 LEDs for minutes
    // Because numbers are small here, we can think about brute force:
    // get all possible combinations of hour LED counts and minute LED counts, and then consider them separately
    // T:O(1) S:O(1)
    fun getPossibleTimes(n: Int): List<String> {
        val ret = mutableListOf<String>()
        var h = 0
        var m = n
        while (m >= 0 && h <= n) {
            if (h >= 4 || m >= 6) {
                continue
            }
            val temp = mutableListOf<String>()
            for (hstr in getHours(h))
                for (mstr in getMinutes(m))
                    temp.add(if (mstr.length == 2) "$hstr:$mstr" else "$hstr:0$mstr")
            ret.addAll(temp)
            h++
            m--
        }
        return ret
    }

    // Given h 1s, get possible result list of hours string
    // T:O(1) S:O(1)
    fun getHours(h: Int): List<String> {
        return (0..11).filter { getNumberOfOnes(it) == h }.map { "$it" }
    }

    // Similar to getHours
    // T:O(1) S:O(1)
    fun getMinutes(m: Int): List<String> {
        return (0..59).filter { getNumberOfOnes(it) == m }.map { "$it" }
    }

    // Get number of 1s in an int
    // T:O(1) S:O(1) considering possible values of num
    fun getNumberOfOnes(num: Int): Int {
        var ret = 0
        var temp = num
        while (temp > 0) {
            temp = temp and (temp - 1)
            ret++
        }
        return ret
    }
}

fun main(args: Array<String>) {
    val s = Solution_binary_watch()
    println(s.getPossibleTimes(1))
}
