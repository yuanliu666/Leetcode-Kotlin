/**
 * Suppose you have a long flowerbed in which some of the plots are planted and some are not.
 * However, flowers cannot be planted in adjacent plots - they would compete for water
 * and both would die.
 *
 * Given a flowerbed (represented as an array containing 0 and 1,
 * where 0 means empty and 1 means not empty), and a number n,
 * return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.
 *
 * Example 1:
 * Input: flowerbed = [1,0,0,0,1], n = 1
 * Output: True
 * Example 2:
 * Input: flowerbed = [1,0,0,0,1], n = 2
 * Output: False
 * Note:
 * The input array won't violate no-adjacent-flowers rule.
 * The input array size is in the range of [1, 20000].
 * n is a non-negative integer which won't exceed the input array size.
 */

class Solution_can_place_flowers {
    // An observation is that greedy works pretty well for this situation
    // Consider [0], [0, 0] and [0, 0, 0], [0, 0, 0, 0], starting with first available spot is always optimal
    // So things are pretty straightforward here: scan from left to right, whenever able to place, place it

    // T:O(n) S:O(1)
    fun canPlaceFlowers(f: Array<Int>, n: Int): Boolean {
        var cnt = 0
        var i = 0
        while (i < f.size) {
            if (f[i] == 0 && (i == 0 || f[i - 1] == 0) && (i == f.size - 1 || f[i + 1] == 0)) {
                cnt++
                if (cnt >= n) {
                    return true
                }
                i++
            }
            i++
        }
        return cnt >= n
    }
}

fun main(args: Array<String>) {
    val s = Solution_can_place_flowers()
    println(s.canPlaceFlowers(arrayOf(1, 0, 0, 0, 1), 1))
    println(s.canPlaceFlowers(arrayOf(1, 0, 0, 0, 1), 2))
}