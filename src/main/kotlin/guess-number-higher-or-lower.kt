/**
 * We are playing the Guess Game. The game is as follows:
 * I pick a number from 1 to n. You have to guess which number I picked.
 * Every time you guess wrong, I will tell you whether the number I picked is higher or lower than your guess.
 * You call a pre-defined API int guess(int num), which returns three possible results:
 * -1: Your guess is higher than the number I picked (i.e. num > pick).
 * 1: Your guess is lower than the number I picked (i.e. num < pick).
 * 0: your guess is equal to the number I picked (i.e. num == pick).
 * Return the number that I picked.
 *
 * 1 <= n <= 2^31 - 1
 * 1 <= pick <= n
 */

class Solution_guess_number_higher_or_lower {

    // T:(lgN) S:O(1)
    fun guessNumber(n: Int): Int {
        var g: Int
        var left = 1
        var right = n
        while (left < right) {
            g = (right - left) / 2 + left
            if (guess(g) == 0) {
                return g
            } else if (guess(g) > 0) {
                left = g + 1
            } else {
                right = g - 1
            }
        }
        return left
    }

    // Used to prevent IDE error
    private fun guess(num: Int): Int {
        return 0
    }
}