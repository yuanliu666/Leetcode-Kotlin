/**
 * There is a malfunctioning keyboard where some letter keys do not work. All other keys on the keyboard work properly.
 * Given a string text of words separated by a single space (no leading or trailing spaces)
 * and a string brokenLetters of all distinct letter keys that are broken,
 * return the number of words in text you can fully type using this keyboard.
 *
 * 1 <= text.length <= 10^4
 * 0 <= brokenLetters.length <= 26
 * text consists of words separated by a single space without any leading or trailing spaces.
 * Each word only consists of lowercase English letters.
 * brokenLetters consists of distinct lowercase English letters.
 */
class Solution_maximum_number_of_words_you_can_type {

    // T:O(n) S:O(m)
    fun canBeTypedWords(text: String, brokenLetters: String): Int {
        val set = hashSetOf<Int>()
        for (c in brokenLetters) {
            set.add(c - 'a')
        }
        var result = 0
        var i = 0
        var canType = true
        while (i < text.length) {
            if (text[i] == ' ') {
                if (canType) {
                    result += 1
                }
                canType = true
            } else if (canType && set.contains(text[i] - 'a')) {
                canType = false
            }
            i += 1
        }
        if (canType) {
            result += 1
        }
        return result
    }
}