/**
 * Given an Android 3x3 key lock screen and two integers m and n,
 * where 1 ≤ m ≤ n ≤ 9, count the total number of unlock patterns of the Android lock screen,
 * which consist of minimum of m keys and maximum n keys.
 *
 * Rules for a valid pattern:
 *
 * 1. Each pattern must connect at least m keys and at most n keys.
 *
 * 2. All the keys must be distinct.
 *
 * 3. If the line connecting two consecutive keys in the pattern passes through any other keys,
 * the other keys must have previously selected in the pattern.
 * No jumps through non selected key is allowed.
 *
 * 4. The order of keys used matters.
 *
 * Explanation:
 *
 *| 1 | 2 | 3 |
 *| 4 | 5 | 6 |
 *| 7 | 8 | 9 |
 *
 *Invalid move: 4 - 1 - 3 - 6
 *Line 1 - 3 passes through key 2 which had not been selected in the pattern.
 *
 *Invalid move: 4 - 1 - 9 - 2
 *Line 1 - 9 passes through key 5 which had not been selected in the pattern.
 *
 *Valid move: 2 - 4 - 1 - 3 - 6
 *Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern
 *
 *Valid move: 6 - 5 - 4 - 1 - 9 - 2
 *Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.
 *
 *Example:
 *Given m = 1, n = 1, return 9.
 */

// T:O(9^2 * 2^9) S:O(9 * 2^9) DP
class Solution_android_unlock_patterns {
    // a key problem here is how to present a valid move
    // we could use a list of int, but actually no need
    // because the previous order does not matter
    // we only need to know what are those numbers and what is the last number
    // hence here comes the idea of use a combination of 9-digit bin plus last number
    // also the problem only ask a number, so DP is preferable

    // set number i used
    // equivalent to set position in bin
    fun merge(used: Int, i: Int): Int {
        return used or (1 shl i)
    }

    // get used numbers count
    // equivalent to get number of 1s in bin
    fun number_of_keys(used: Int): Int {
        var number = 0
        var temp = used
        while (temp > 0) {
            temp = temp and (temp - 1)
            number++
        }

        return number
    }

    // get whether number i is used
    // equivalent to get if position is set in bin
    fun contain(used: Int, i: Int): Boolean {
        return used and (1 shl i) > 0
    }

    // convert from coordinate display (i,j) of 3x3 matrix to 1-9 digits
    fun convert(i: Int, j: Int): Int {
        return 3 * i + j
    }

    fun getCount(m: Int, n: Int): Int {
        // dp[i][j], where i is the bin, j + 1 is last number
        // hence we have 1 << 9 rows and 9 columns
        val dp = Array(1 shl 9, { IntArray(9) })
        // set init values
        (0..8).forEach { dp[merge(0, it)][it] = 1 }

        var ret = 0
        // why is this order valid?
        // it's from 1 to 0x111111111, bin is ascending
        // if you look at the code, we only care about dp[merge(used, j)][j] += dp[used][i]
        // namely requires merge(used, j) comes after used
        // because merge(used, j) > used, so it is true (j is not in used)
        for (used in (0..dp.size - 1)) {
            val number = number_of_keys(used)
            // numbers count can vary many times
            if (number > n)
                continue
            for (i in 0..8) {
                if (!contain(used, i))
                    continue
                // add counts ending with i and with used bin
                if (number in m..n)
                    ret += dp[used][i]
                // get coordinates of last number
                val x1 = i / 3
                val y1 = i % 3
                for (j in 0..8) {
                    // do not allow duplicate
                    if (contain(used, j))
                        continue
                    val x2 = j / 3
                    val y2 = j % 3
                    // check if cross unused number
                    if (((x1 == x2 && Math.abs(y1 - y2) == 2)
                            || (y1 == y2 && Math.abs(x1 - x2) == 2)
                            || (Math.abs(x1 - x2) == 2 && Math.abs(y1 - y2) == 2))
                            && !contain(used, convert((x1 + x2) / 2, (y1 + y2) / 2)))
                        continue
                    dp[merge(used, j)][j] += dp[used][i]
                }
            }
        }
        return ret
    }
}

// DFS solution using skip tables and symmetry
class Solution_android_unlock_patterns2 {
    // cur: the current position
    // remain: the steps remaining
    internal fun DFS(vis: BooleanArray, skip: Array<IntArray>, cur: Int, remain: Int): Int {
        if (remain < 0) return 0
        if (remain == 0) return 1
        vis[cur] = true
        var rst = 0
        (1..9).forEach {
            // If vis[i] is not visited and (two numbers are adjacent or skip number is already visited)
            if (!vis[it] && (skip[cur][it] == 0 || vis[skip[cur][it]])) {
                rst += DFS(vis, skip, it, remain - 1)
            }
        }
        vis[cur] = false
        return rst
    }

    fun numberOfPatterns(m: Int, n: Int): Int {
        // Skip array represents number to skip between two pairs
        val skip = Array(10) { IntArray(10) }
        skip[3][1] = 2
        skip[1][3] = skip[3][1]
        skip[7][1] = 4
        skip[1][7] = skip[7][1]
        skip[9][3] = 6
        skip[3][9] = skip[9][3]
        skip[9][7] = 8
        skip[7][9] = skip[9][7]
        skip[6][4] = 5
        skip[4][6] = skip[6][4]
        skip[7][3] = skip[4][6]
        skip[3][7] = skip[7][3]
        skip[8][2] = skip[3][7]
        skip[2][8] = skip[8][2]
        skip[9][1] = skip[2][8]
        skip[1][9] = skip[9][1]
        val vis = BooleanArray(10)
        var rst = 0
        // DFS search each length from m to n
        for (i in m..n) {
            rst += DFS(vis, skip, 1, i - 1) * 4    // 1, 3, 7, 9 are symmetric
            rst += DFS(vis, skip, 2, i - 1) * 4    // 2, 4, 6, 8 are symmetric
            rst += DFS(vis, skip, 5, i - 1)        // 5
        }
        return rst
    }
}

fun main(args: Array<String>) {
    println(Solution_android_unlock_patterns().getCount(2, 2))
    println(Solution_android_unlock_patterns2().numberOfPatterns(2, 2))
}
