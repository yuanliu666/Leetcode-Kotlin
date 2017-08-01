/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 *
 * Each time you can either climb 1 or 2 steps.
 * In how many distinct ways can you climb to the top?
 */

class Solution_climbing_stairs {
    // T:O(n) S:O(1)
    fun getClimbWays(n: Int): Int {
        var prev = 0
        var cur = 1
        for (i in 1..n) {
            val temp = prev + cur
            prev = cur
            cur = temp
        }
        return cur
    }
}

fun main(args: Array<String>) {
    val s = Solution_climbing_stairs()
    println(s.getClimbWays(1))
    println(s.getClimbWays(2))
    println(s.getClimbWays(3))
    println(s.getClimbWays(4))
}