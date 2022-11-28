/**
 * Given an array of characters chars, compress it using the following algorithm:
 * Begin with an empty string s. For each group of consecutive repeating characters in chars:
 * If the group's length is 1, append the character to s.
 * Otherwise, append the character followed by the group's length.
 * The compressed string s should not be returned separately, but instead,
 * be stored in the input character array chars.
 * Note that group lengths that are 10 or longer will be split into multiple characters in chars.
 * After you are done modifying the input array, return the new length of the array.
 * You must write an algorithm that uses only constant extra space.
 */
class Solution_string_compression {

    // Two pointers, write in place. T:O(n) S:O(1)
    fun compress(chars: CharArray): Int {
        // Read and write pointers
        var r = 0
        var w = 0
        while (r < chars.size) {
            var p = r + 1
            while (p < chars.size && chars[p] == chars[r]) {
                p++
            }
            val cnt = p - r
            chars[w] = chars[r]
            w++
            if (cnt > 1) {
                for (c in cnt.toString()) {
                    chars[w++] = c
                }
            }
            r = p
        }
        return w
    }
}