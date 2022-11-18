/**
 * Given a string of English letters s, return the greatest English letter which occurs as both a lowercase and uppercase letter in s.
 * The returned letter should be in uppercase. If no such letter exists, return an empty string.
 * An English letter b is greater than another letter a if b appears after a in the English alphabet.
 */

class Solution_greatest_english_letter_in_upper_and_lower_case {

    // T:O(n) S:O(1)
    fun greatestLetter(s: String): String {
        val arr = IntArray(26)
        for (c in s) {
            if (c.isLowerCase()) {
                arr[c - 'a'] = arr[c - 'a'] or 1
            }
            if (c.isUpperCase()) {
                arr[c - 'A'] = arr[c - 'A'] or 2
            }
        }
        for (i in arr.indices.reversed()) {
            if (arr[i] == 3) {
                val n: Char = 'A' + i
                return n.toString()
            }
        }
        return ""
    }
}