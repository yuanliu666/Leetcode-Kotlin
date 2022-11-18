import java.lang.StringBuilder

/**
 * A fancy string is a string where no three consecutive characters are equal.
 * Given a string s, delete the minimum possible number of characters from s to make it fancy.
 * Return the final string after the deletion. It can be shown that the answer will always be unique.
 *
 * 1 <= s.length <= 10^5
 * s consists only of lowercase English letters.
 */
class Solution_delete_characters_to_make_fancy_string {

    // T:O(n) S:O(n)
    fun makeFancyString(s: String): String {
        val sb = StringBuilder()
        for (i in s.indices) {
            if (i < 2 || !(s[i] == s[i - 1] && s[i - 1] == s[i - 2])) {
                sb.append(s[i])
            }
        }
        return sb.toString()
    }
}