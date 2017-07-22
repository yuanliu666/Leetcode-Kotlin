/**
 * In the "100 game," two players take turns adding, to a running total, any integer from 1..10.
 * The player who first causes the running total to reach or exceed 100 wins.
 *
 * What if we change the game so that players cannot re-use integers?
 *
 * For example, two players might take turns drawing from a common pool of numbers of 1..15
 * without replacement until they reach a total >= 100.
 *
 * Given an integer maxChoosableInteger and another integer desiredTotal,
 * determine if the first player to move can force a win, assuming both players play optimally.
 *
 * You can always assume that maxChoosableInteger will not be larger than 20 and
 * desiredTotal will not be larger than 300.
 *
 * Example
 *
 * Input:
 * maxChoosableInteger = 10
 * desiredTotal = 11
 *
 * Output:
 * false
 *
 * Explanation:
 * No matter which integer the first player choose, the first player will lose.
 * The first player can choose an integer from 1 up to 10.
 * If the first player choose 1, the second player can only choose integers from 2 up to 10.
 * The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
 * Same with other integers chosen by the first player, the second player will always win.
 */

class Solution_can_i_win {
    // A key for this problem is how to track selections
    // Because we want some memorization other than pure brute force
    // We can use a n-bit mask here, 1 at ith position from right means i has been used
    // So let lookup[mask] means based on previous selections, current player can win or not

    // T:O(n!) S:O(n)
    fun isIWin(n: Int, d: Int): Boolean {
        if ((1 + n) * n / 2 < d) {
            return false
        }
        return helper(n, d, 0, mutableMapOf())
    }

    fun helper(n: Int, d: Int, visited: Int, lookup: MutableMap<Int, Boolean>): Boolean {
        if (lookup.containsKey(visited)) {
            return lookup[visited]!!
        }

        var mask = 1
        for (i in 1..n) {
            if (visited and mask == 0) {
                // this number is not used yet
                if (i >= d || !helper(n, d - i, visited or mask, lookup)) {
                    // win now or next player lose with next round selections
                    lookup[visited] = true
                    return true
                }
            }
            mask = mask shl 1
        }
        lookup[visited] = false
        return false
    }
}

fun main(args: Array<String>) {
    val s = Solution_can_i_win()
    println(s.isIWin(10, 1))
    println(s.isIWin(10, 11))
    println(s.isIWin(10, 20))
    println(s.isIWin(10, 55))
    println(s.isIWin(10, 56))
    println(s.isIWin(9, 45))
}