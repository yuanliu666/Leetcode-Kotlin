/**
 * You are playing the following Bulls and Cows game with your friend:
 * You write a 4-digit secret number and ask your friend to guess it,
 * each time your friend guesses a number, you give a hint, the hint
 * tells your friend how many digits are in the correct positions
 * (called "bulls") and how many digits are in the wrong positions
 * (called "cows"), your friend will use those hints to find out the
 * secret number.
 *
 * For example:
 *
 * Secret number:  1807
 * Friend's guess: 7810
 * Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.)
 * According to Wikipedia: "Bulls and Cows (also known as Cows and Bulls
 * or Pigs and Bulls or Bulls and Cleots) is an old code-breaking mind or
 * paper and pencil game for two or more players, predating the similar
 * commercially marketed board game Mastermind. The numerical version of
 * the game is usually played with 4 digits, but can also be played with
 * 3 or any other number of digits."
 *
 * Write a function to return a hint according to the secret number and
 * friend's guess, use A to indicate the bulls and B to indicate the cows,
 * in the above example, your function should return 1A3B.
 *
 * You may assume that the secret number and your friend's guess only contain
 * digits, and their lengths are always equal.
 */

class Solution_bulls_and_cows {
    // T:O(n) S:O(1) because only 0-9 char
    fun getBullsAndCows(s: String, g: String): String {
        val dict = mutableMapOf<Char, Int>()
        // get count map
        s.toCharArray().forEach {
            dict.putIfAbsent(it, 0)
            dict.put(it, dict[it]!! + 1)
        }
        var b = 0
        var c = 0
        s.zip(g).forEach {
            if (it.first == it.second) {
                // bull should always count
                dict.put(it.second, dict[it.second]!! - 1)
                b++
                // in case cow for this number has been counted before but actually should not count, revert
                // e.g. 0088 and 8888, 2 bulls and 0 cows
                if (dict[it.second]!! < 0) {
                    dict.put(it.second, dict[it.second]!! + 1)
                    c--
                }
            } else if (dict.containsKey(it.second) && dict[it.second]!! > 0) {
                dict.put(it.second, dict[it.second]!! - 1)
                c++
            }
        }
        return "${b}A${c}B"
    }

    // One pass with two maps
    // Use two maps to store the counts of chars so far in s and g
    // When a new pair comes, if equal then add a bull and continue
    // When not equal, check if there is any cow
    //T:O(n) S:O(1)
    fun getBullsAndCows2(s: String, g: String): String {
        val sMap = mutableMapOf<Char, Int>()
        val gMap = mutableMapOf<Char, Int>()
        var b = 0
        var c = 0
        s.zip(g).forEach {
            if (it.first == it.second) {
                b++
            } else {
                if (sMap.containsKey(it.second)) {
                    c++
                    sMap.put(it.second, sMap[it.second]!! - 1)
                } else {
                    gMap.putIfAbsent(it.second, 0)
                    gMap.put(it.second, gMap[it.second]!! + 1)
                }
                if (gMap.containsKey(it.first)) {
                    c++
                    gMap.put(it.first, gMap[it.first]!! - 1)
                } else {
                    sMap.putIfAbsent(it.first, 0)
                    sMap.put(it.first, sMap[it.first]!! + 1)
                }
            }
        }
        return "${b}A${c}B"
    }
}

fun main(args: Array<String>) {
    val s = Solution_bulls_and_cows()
    println(s.getBullsAndCows("1807", "7810"))
    println(s.getBullsAndCows2("1807", "7810"))
    println(s.getBullsAndCows("0088", "8888"))
    println(s.getBullsAndCows2("0088", "8888"))
}