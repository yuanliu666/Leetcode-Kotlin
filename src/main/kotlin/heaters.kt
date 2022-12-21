/**
 * Winter is coming! During the contest, your first job is to design a standard heater with a fixed warm radius to warm all the houses.
 * Every house can be warmed, as long as the house is within the heater's warm radius range.
 * Given the positions of houses and heaters on a horizontal line, return the minimum radius standard of heaters so that those heaters could cover all houses.
 * Notice that all the heaters follow your radius standard, and the warm radius will the same.
 */
class Solution_heaters {

    // Binary search. T:O((m+n)*logm), where n is size of houses and m is size of heaters; T:O(1)
    fun findRadius(houses: IntArray, heaters: IntArray): Int {
        heaters.sort()
        var d = 0
        for (h in houses) {
            d = kotlin.math.max(d, getMinDistance(h - 1, heaters))
        }
        return d
    }

    private fun getMinDistance(h: Int, heaters: IntArray): Int {
        if (h <= heaters[0] - 1) return heaters[0] - 1 - h
        if (h >= heaters.last() - 1) return h - heaters.last() + 1
        var lo = 0
        var hi = heaters.size - 1
        while (lo < hi) {
            val m = (hi - lo) / 2 + lo
            val v = heaters[m] - 1
            when {
                v == h -> return 0
                h < v -> hi = m - 1
                else -> lo = m + 1
            }
        }
        var ret = kotlin.math.abs(h - heaters[lo] + 1)
        if (lo - 1 >= 0) {
            ret = kotlin.math.min(ret, kotlin.math.abs(h - heaters[lo - 1] + 1))
        }
        if (lo + 1 < heaters.size) {
            ret = kotlin.math.min(ret, kotlin.math.abs(h - heaters[lo + 1] + 1))
        }
        return ret
    }
}