/**
 * There is a room with n lights which are turned on initially and 4 buttons on the wall.
 * After performing exactly m unknown operations towards buttons,
 * you need to return how many different kinds of status of the n lights could be.
 *
 * Suppose n lights are labeled as number [1, 2, 3 ..., n], function of these 4 buttons are given below:
 *
 * Flip all the lights.
 * Flip lights with even numbers.
 * Flip lights with odd numbers.
 * Flip lights with (3k + 1) numbers, k = 0, 1, 2, ...
 * Example 1:
 * Input: n = 1, m = 1.
 * Output: 2
 * Explanation: Status can be: \[on], \[off]
 *
 * Example 2:
 * Input: n = 2, m = 1.
 * Output: 3
 * Explanation: Status can be: [on, off], [off, on], [off, off]
 *
 * Example 3:
 * Input: n = 3, m = 1.
 * Output: 4
 * Explanation: Status can be: [off, on, off], [on, off, on], [off, off, off], [off, on, on].
 * Note: n and m both fit in range [0, 1000].
 */

class Solution_bulb_switcher_ii {

    // use `o`represents bulb on, `x` represents off
    // use Bi to represent ith button pressed

    // simple fact: suppose n is large enough, with B1B2B3, we can get 4 symmetric results for m = 2
    // 1. all on (any two same action)
    // 2. all off (B2B3)
    // 3. even on, odd off (B1B2)
    // 4. even off, odd on (B1B3)
    // but with m = 1 we can only get 3, except all on because all 3 buttons will change status
    // and with m >= 3 we can get all 4 results
    // because 2 same actions cancel each other, hence m = 4, 6, 8... already contain m = 2's results
    // for m = 3, it already contains results from m = 1, and plus all on: B1B2B3
    // hence for m >= 2 we can get those 4 symmetric results
    // with those, B4 can produce another 4 different results, so max of 8
    // but it is possible that n is small that some buttons do not work as expected
    // so we need to check specifically

    // T:O(1) S:O(1)
    fun flipLights(n: Int, m: Int): Int {
        return when {
            n == 1 -> if (m >= 1) {
                2
            } else {
                1
            }

        // when n == 2, B3 B4 act same
            n == 2 -> {
                when {
                // [xx, ox, xo]
                    m == 1 -> 3
                // plus oo
                    m >= 2 -> 4
                    else -> 1
                }
            }

        // when n >= 3, all 4 buttons will have different effects
            n >= 3 -> {
                when {
                // 4 buttons have 4 different effects
                // assume n = 4,
                // results are [xxxx,oxox,xoxo,xoox]
                // it does not matter when n is bigger
                    m == 1 -> 4
                // results are [oooo, xxxx, oxox, xoxo, oxxo, xxoo, ooxx]
                // first 4 results are from first 3 buttons
                // last 3 results are from previous results with button 4
                // note xoox is not possible here
                    m == 2 -> 7
                // [oooo, xxxx, oxox, xoxo] is possible starting from m = 2, m = 3 can also do that
                // and then the 4 results addon B4
                // total 8 results
                    m >= 3 -> 8
                    else -> 1
                }
            }
            else -> 0
        }
    }
}

fun main(args: Array<String>) {
    // LC OJ passed
    val s = Solution_bulb_switcher_ii()
    println(s.flipLights(1, 1))
    println(s.flipLights(2, 1))
    println(s.flipLights(3, 1))
}