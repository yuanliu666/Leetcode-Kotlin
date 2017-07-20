/**
 * There are n bulbs that are initially off.
 * You first turn on all the bulbs. Then,
 * you turn off every second bulb. On the
 * third round, you toggle every third bulb
 * (turning on if it's off or turning off if
 * it's on). For the nth round, you only
 * toggle the last bulb. Find how many bulbs
 * are on after n rounds.
 *
 * Example:
 *
 * Given n = 3.
 *
 * At first, the three bulbs are [off, off, off].
 * After first round, the three bulbs are [on, on, on].
 * After second round, the three bulbs are [on, off, on].
 * After third round, the three bulbs are [on, off, off].
 *
 * So you should return 1, because there is
 * only one bulb is on.
 */

class Solution_bulb_switcher {
    // This problem is more like a math or intelligent one
    // If we take a close look at the nth bulb, it is clearly that this bulb's toggle times equal to its factor numbers
    // Namely, 1st bulb has factor [1], so it will be toggle once; 2nd bulb has factors of [1, 2] so twice
    // How could this help?
    // Say n has a list of factors, any factor a would have another factor b that let a * b = n
    // a and b might equal. In this case, a = b = sqrt(n)
    // If a and b are not equal, this bulb will be triggered twice, or you can think those two cancel each other
    // So at last, only those with equal factor pair, or square numbers, will have odd trigger counts.
    // T:O(1) S:O(1)
    fun getRet(n: Int): Int {
        return Math.sqrt(n.toDouble()).toInt()
    }
}

fun main(args: Array<String>) {
    println(Solution_bulb_switcher().getRet(3))
}