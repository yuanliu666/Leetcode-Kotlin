/**
 * You are given a string s and an integer k.
 * You can choose any character of the string and change it to any other uppercase English character.
 * You can perform this operation at most k times.
 * Return the length of the longest substring containing the same letter you can get after performing the above operations.
 */
class Solution_longest_repeating_character_replacement {

    //T:(n) S:O(1)
    fun characterReplacement(s: String, k: Int): Int {
        val count = IntArray(26)
        var start = 0
        var maxCount = 0
        var maxLength = 0
        // For a substring, if its max count of same char + k >= len,
        // then we know that changing all other chars to this char can make whole string same chars
        // Using slide window of start to end, when we first find a good window, we can think about how to move it:
        // if next char is still max count char, we are happy and just update result;
        // otherwise, since we are looking for max result, it shouldn't be smaller than previous one,
        // so the window should not shrink, and we just move it right
        for (end in s.indices) {
            // Adding end to window
            count[s[end] - 'A']++
            // max count here is historic max count, not current max count in window
            // But it is ok, since k is fixed, if we want to beat previous max len, we need higher max count
            maxCount = Math.max(maxCount, count[s[end] - 'A'])
            // If max count goes up, then it will successfully pass check since windowLen and maxCount both add 1
            // Think about case of k = 1 and something like AABAB...CCDCC.
            // We will have window size of 4 on start with max count of 3,
            // but when window is at "CCDC", the next char will refresh max count and makes window expansion success
            val windowLen = end - start + 1
            if (k + maxCount < windowLen) {
                // Expansion failed, so need to move start, equal to move window to right by 1
                count[s[start] - 'A']--
                start++
            }
            // If it is expansion we'll get new max
            maxLength = Math.max(maxLength, end - start + 1)
        }
        return maxLength
    }
}