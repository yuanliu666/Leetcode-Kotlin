/**
 * Given two strings s and t, return true if they are equal when both are typed into empty text editors.
 * '#' means a backspace character.
 * Note that after backspacing an empty text, the text will continue empty.
 */
class Solution_backspace_string_comparison {

    // T:O(n) S:O(n)
    fun backspaceCompare(s: String, t: String): Boolean {
        return convert(s) == convert(t)
    }

    private fun convert(s: String): String {
        val sb = StringBuilder()
        var cnt = 0
        for (c in s.reversed()) {
            when (c) {
                '#' -> cnt++
                else -> {
                    if (cnt > 0) {
                        cnt--
                    } else {
                        sb.append(c)
                    }
                }
            }
        }
        return sb.toString()
    }
}