/**
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 * Note:
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * Only the filled cells need to be validated according to the mentioned rules.
 *
 * board.length == 9
 * board[i].length == 9
 * board[i][j] is a digit 1-9 or '.'.
 */
class Solution_valid_sudoku {

    // T:O(1) S:O(1)
    fun isValidSudoku(board: Array<CharArray>): Boolean {
        // validate rows
        for (r in board.indices) {
            val arr = IntArray(10)
            for (c in board[0].indices) {
                if (board[r][c] != '.') {
                    val cur = board[r][c] - '0'
                    arr[cur]++
                    if (arr[cur] > 1) return false
                }
            }
        }
        // validate cols
        for (c in board[0].indices) {
            val arr = IntArray(10)
            for (r in board.indices) {
                if (board[r][c] != '.') {
                    val cur = board[r][c] - '0'
                    arr[cur]++
                    if (arr[cur] > 1) return false
                }
            }
        }
        // validate small boxes
        for (r in board.indices.step(3)) {
            for (c in board[0].indices.step(3)) {
                val arr = IntArray(10)
                for (i in r until r + 3) {
                    for (j in c until c + 3) {
                        if (board[i][j] != '.') {
                            val cur = board[i][j] - '0'
                            arr[cur]++
                            if (arr[cur] > 1) return false
                        }
                    }
                }
            }
        }
        return true
    }
}