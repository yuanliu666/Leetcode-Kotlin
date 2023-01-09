/**
 * You have n flower seeds. Every seed must be planted first before it can begin to grow, then bloom.
 * Planting a seed takes time and so does the growth of a seed.
 * You are given two 0-indexed integer arrays plantTime and growTime, of length n each:
 * plantTime[i] is the number of full days it takes you to plant the ith seed. Every day,
 * you can work on planting exactly one seed. You do not have to work on planting the same seed on consecutive days,
 * but the planting of a seed is not complete until you have worked plantTime[i] days on planting it in total.
 * growTime[i] is the number of full days it takes the ith seed to grow after being completely planted.
 * After the last day of its growth, the flower blooms and stays bloomed forever.
 * From the beginning of day 0, you can plant the seeds in any order.
 * Return the earliest possible day where all seeds are blooming.
 */
class Solution_earliest_possible_day_of_full_bloom {

    // One observation is that all plant time needs to be used. Then it is a matter of which one to plant first.
    // A common sense is to grow ones that grow slower first.
    // T:O(nlogn) S:O(n)
    fun earliestFullBloom(plantTime: IntArray, growTime: IntArray): Int {
        val n = growTime.size
        val indices: MutableList<Int> = ArrayList()
        for (i in 0 until n) {
            indices.add(i)
        }
        indices.sortBy { -growTime[it] }
        var result = 0
        var i = 0
        var curPlantTime = 0
        while (i < n) {
            val idx = indices[i]
            val time = curPlantTime + plantTime[idx] + growTime[idx]
            result = Math.max(result, time)
            curPlantTime += plantTime[idx]
            ++i
        }
        return result
    }
}