/**
 * Given a string s containing an out-of-order English representation of digits 0-9, return the digits in ascending order.
 *
 * 1 <= s.length <= 10^5
 * s[i] is one of the characters ["e","g","f","i","h","o","n","s","r","u","t","w","v","x","z"].
 * s is guaranteed to be valid.
 */
class Solution_reconstruct_orignal_digits_from_english {

    // T:O(n) S:O(n)
    fun originalDigits(s: String): String {
        val arr = IntArray(10)
        val map = hashMapOf<Char, Int>()
        for (c in s) {
            map.putIfAbsent(c, 0)
            map[c] = map[c]!! + 1
        }
        // deal with zero
        val countZero = map['z'] ?: 0
        arr[0] = countZero
        for (c in "zero") {
            map[c] = (map[c] ?: 0) - countZero
        }
        // deal with six
        val countSix = map['x'] ?: 0
        arr[6] = countSix
        for (c in "six") {
            map[c] = (map[c] ?: 0) - countSix
        }
        // deal with two
        val countTwo = map['w'] ?: 0
        arr[2] = countTwo
        for (c in "two") {
            map[c] = (map[c] ?: 0) - countTwo
        }
        // deal with four
        val countFour = map['u'] ?: 0
        arr[4] = countFour
        for (c in "four") {
            map[c] = (map[c] ?: 0) - countFour
        }
        // deal with five
        val countFive = map['f'] ?: 0
        arr[5] = countFive
        for (c in "five") {
            map[c] = (map[c] ?: 0) - countFive
        }
        // deal with seven
        val countSeven = map['v'] ?: 0
        arr[7] = countSeven
        for (c in "seven") {
            map[c] = (map[c] ?: 0) - countSeven
        }
        // deal with eight
        val countEight = map['g'] ?: 0
        arr[8] = countEight
        for (c in "eight") {
            map[c] = (map[c] ?: 0) - countEight
        }
        // deal with three
        val countThree = map['t'] ?: 0
        arr[3] = countThree
        for (c in "three") {
            map[c] = (map[c] ?: 0) - countThree
        }
        // deal with one
        val countOnr = map['o'] ?: 0
        arr[1] = countOnr
        for (c in "one") {
            map[c] = (map[c] ?: 0) - countOnr
        }
        // deal with nine
        arr[9] = map['i'] ?: 0
        // get result
        val sb = StringBuilder()
        for (i in arr.indices) {
            val ch:Char = '0' + i
            val str = ch.toString().repeat(arr[i])
            sb.append(str)
        }
        return sb.toString()
    }
}