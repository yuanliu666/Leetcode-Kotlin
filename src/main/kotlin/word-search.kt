/**
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cells,
 * where adjacent cells are horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 *
 * Constraints:
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board and word consists of only lowercase and uppercase English letters.
 *
 * Follow up: Could you use search pruning to make your solution faster with a larger board?
 */
class Solution_word_search {

    // DFS solution. T: O(N*3^L) where L is length of word and N is number of cells in the board.
    // Because after first step, we won't go back, so there are just 3 choices instead of 4.
    // S:O(N) as I keep a visited set. If can alter the board, this one can be saved then it becomes O(L).
    fun exist(board: Array<CharArray>, word: String): Boolean {
        val m = board.size
        val n = board[0].size
        if (word.length > m * n) return false
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (board[i][j] == word[0] && helper(board, word, i, j, 0, hashSetOf())) {
                    return true
                }
            }
        }
        return false
    }

    private val dir = listOf(listOf(1, 0), listOf(0, 1), listOf(-1, 0), listOf(0, -1))

    private fun helper(
        board: Array<CharArray>,
        word: String,
        r: Int,
        c: Int,
        idx: Int,
        visited: HashSet<Int>
    ): Boolean {
        if (idx == word.length - 1) return true
        val m = board.size
        val n = board[0].size
        visited.add(r * n + c)

        for (d in dir) {
            val nr = r + d[0]
            val nc = c + d[1]
            if (nr in 0 until m
                && nc in 0 until n
                && !visited.contains(nr * n + nc)
                && board[nr][nc] == word[idx + 1]
                && helper(board, word, nr, nc, idx + 1, visited)
            ) {
                return true
            }
        }
        visited.remove(r * n + c)
        return false
    }
}