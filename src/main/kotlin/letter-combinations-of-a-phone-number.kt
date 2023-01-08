/**
 * Given a string containing digits from 2-9 inclusive,
 * return all possible letter combinations that the number could represent. Return the answer in any order.
 * A mapping of digits to letters (just like on the telephone buttons) is given below.
 * Note that 1 does not map to any letters.
 *
 * Constraints:
 * 0 <= digits.length <= 4
 * digits[i] is a digit in the range ['2', '9'].
 */
class Solution_letter_combinations_of_a_phone_number {

    // DFS with backtracking. T:O(4^n) S:O(n)
    fun letterCombinations(digits: String): List<String> {
        if (digits.isEmpty()) return emptyList()
        val map = mapOf(
            '2' to charArrayOf('a', 'b', 'c'),
            '3' to charArrayOf('d', 'e', 'f'),
            '4' to charArrayOf('g', 'h', 'i'),
            '5' to charArrayOf('j', 'k', 'l'),
            '6' to charArrayOf('m', 'n', 'o'),
            '7' to charArrayOf('p', 'q', 'r', 's'),
            '8' to charArrayOf('t', 'u', 'v'),
            '9' to charArrayOf('w', 'x', 'y', 'z')
        )
        val ret = mutableListOf<String>()
        dfs(digits, 0, java.lang.StringBuilder(), ret, map)
        return ret
    }

    private fun dfs(
        digits: String,
        cur: Int,
        path: StringBuilder,
        ret: MutableList<String>,
        map: Map<Char, CharArray>
    ) {
        if (cur == digits.length) {
            ret.add(path.toString())
            return
        }
        for (c in map[digits[cur]]!!) {
            path.append(c)
            dfs(digits, cur + 1, path, ret, map)
            path.deleteCharAt(path.lastIndex)
        }
    }
}