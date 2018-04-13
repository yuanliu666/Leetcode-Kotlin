/**
 * Given a set of keywords words and a string S, make all appearances of all keywords in S bold.
 * Any letters between <b> and </b> tags become bold.
 *
 * The returned string should use the least number of tags possible,
 * and of course the tags should form a valid combination.
 *
 * For example, given that words = ["ab", "bc"] and S = "aabcd",
 * we should return "a<b>abc</b>d". Note that returning "a<b>a<b>b</b>c</b>d" would use more tags, so it is incorrect.
 *
 * Note:
 *
 * 1. words has length in range [0, 50].
 * 2. words\[i] has length in range [1, 10].
 * 3. S has length in range [0, 500].
 * 4. All characters in words\[i] and S are lowercase letters.
 */

import java.lang.StringBuilder

class Solution_bold_words_in_string {

    // this is identical to `add-bold-tag-in-string` except duplicate key words is possible in str
    // T:O(n*w*l) where l is the average length of words, S:O(n)
    fun getBoldString(words: Array<String>, str: String): String {
        // list to indicate if corresponding char in str is bold
        val boldLookup = MutableList(str.length, { false })
        // search up all occurrence of key words and set bold to true
        for (w in words) {
            var pos = str.indexOf(w)
            while (pos != -1) {
                for (i in pos until pos + w.length) {
                    boldLookup[i] = true
                }
                pos = str.indexOf(w, pos + 1)
            }
        }
        // process result
        val result = StringBuilder()
        for (i in 0 until str.length) {
            // check start boundary
            if (boldLookup[i] && (i == 0 || !boldLookup[i - 1])) {
                result.append("<b>")
            }
            result.append(str[i])
            // check end boundary
            if (boldLookup[i] && (i == str.length - 1 || !boldLookup[i + 1])) {
                result.append("</b>")
            }
        }
        return result.toString()
    }
}

fun main(args: Array<String>) {
    val s = Solution_bold_words_in_string()
    println(s.getBoldString(arrayOf("ab", "bc"), "aabcd"))
    println(s.getBoldString(arrayOf("aa"), "aaaababbaaab"))
}