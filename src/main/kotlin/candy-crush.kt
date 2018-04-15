import kotlin.math.abs

/**
 * This question is about implementing a basic elimination algorithm for Candy Crush.
 *
 * Given a 2D integer array board representing the grid of candy,
 * different positive integers board\[i]\[j] represent different types of candies.
 * A value of board\[i]\[j] = 0 represents that the cell at position (i, j) is empty.
 * The given board represents the state of the game following the player's move.
 * Now, you need to restore the board to a stable state by crushing candies according to the following rules:
 *
 * If three or more candies of the same type are adjacent vertically or horizontally,
 * "crush" them all at the same time - these positions become empty.
 *
 * After crushing all candies simultaneously,
 * if an empty space on the board has candies on top of itself,
 * then these candies will drop until they hit a candy or bottom at the same time.
 * (No new candies will drop outside the top boundary.)
 *
 * After the above steps, there may exist more candies that can be crushed.
 * If so, you need to repeat the above steps.
 * If there does not exist more candies that can be crushed (ie. the board is stable),
 * then return the current board.
 * You need to perform the above rules until the board becomes stable, then return the current board.
 *
 * Example 1:
 * Input:
 * board =
 * [[110,5,112,113,114],
 * [210,211,5,213,214],
 * [310,311,3,313,314],
 * [410,411,412,5,414],
 * [5,1,512,3,3],
 * [610,4,1,613,614],
 * [710,1,2,713,714],
 * [810,1,2,1,1],
 * [1,1,2,2,2],
 * [4,1,4,4,1014]]
 * Output:
 * [[0,0,0,0,0],
 * [0,0,0,0,0],
 * [0,0,0,0,0],
 * [110,0,0,0,114],
 * [210,0,0,0,214],
 * [310,0,0,113,314],
 * [410,0,0,213,414],
 * [610,211,112,313,614],
 * [710,311,412,613,714],
 * [810,411,512,713,1014]]

 * Note:
 * The length of board will be in the range [3, 50].
 * The length of board\[i] will be in the range [3, 50].
 * Each board\[i]\[j] will initially start as an integer in the range [1, 2000].
 */

class Solution_candy_crush {


    // a straightforward idea: crush -> fall, repeat this process until no crush happens
    // now problem breaks to 2 problems: how to perform crush and how to perform fall

    // original solution==========================================
    // T:O((m*m*n)^2) S:O(m*n)
    fun getStableBoard(board: Array<IntArray>): Array<IntArray> {
        var map: Map<Pair<Int, Int>, Boolean>
        while (true) {
            map = getCrushIndex(board)
            if (map.isEmpty()) {
                break
            } else {
                performFall(board, map)
            }
        }
        return board
    }

    // T:O(m*n) S:O(m*n)
    private fun getCrushIndex(board: Array<IntArray>): Map<Pair<Int, Int>, Boolean> {
        val indexMap = HashMap<Pair<Int, Int>, Boolean>()
        for (i in 0 until board.size) {
            for (j in 0 until board[0].size) {
                if (board[i][j] == 0) {
                    continue
                } else {
                    // check vertically
                    // we only focus on top point for simplicity
                    // this can also avoid repeat
                    if (i + 2 < board.size
                            && board[i][j] == board[i + 1][j]
                            && board[i + 2][j] == board[i][j]
                            && (i == 0 || board[i][j] != board[i - 1][j])) {
                        // cannot set 0 for now because it can affect other crush
                        var k = 0
                        while (i + k < board.size && board[i][j] == board[i + k][j]) {
                            indexMap[Pair(i + k, j)] = true
                            k++
                        }
                    }
                    // check horizontally, focus on left point
                    if (j + 2 < board[0].size
                            && board[i][j] == board[i][j + 1] && board[i][j] == board[i][j + 2]
                            && (j == 0 || board[i][j] != board[i][j - 1])) {
                        var k = 0
                        while (j + k < board[0].size && board[i][j] == board[i][j + k]) {
                            indexMap[Pair(i, j + k)] = true
                            k++
                        }
                    }
                }
            }
        }
        return indexMap
    }

    // I feel there should be a better solution to perform fall but I cannot come up in a short time
    // T:O(m*m*n)
    private fun performFall(board: Array<IntArray>, indexMap: Map<Pair<Int, Int>, Boolean>): Array<IntArray> {
        // left to right
        for (j in 0 until board[0].size) {
            var i = 0
            // top to bottom
            while (i < board.size) {
                if (indexMap.containsKey(Pair(i, j))) {
                    // fall
                    if (i > 0) {
                        for (k in i downTo 1) {
                            board[k][j] = board[k - 1][j]
                        }
                    }
                    board[0][j] = 0
                }
                i++
            }
        }
        return board
    }


    // enhanced solution=============================================================
    // use negative value in place instead of map to mark crush
    // and also use a better fall algorithm
    // T:O((m*n)^2) S:O(1)
    fun getStableBoard2(board: Array<IntArray>): Array<IntArray> {
        while (true) {
            if (markCrush(board)) {
                performFall(board)
            } else {
                break
            }
        }
        return board
    }

    // return has crush or not
    // T:O(m*n) S:O(1)
    private fun markCrush(board: Array<IntArray>): Boolean {
        var hasCrush = false
        for (i in 0 until board.size) {
            for (j in 0 until board[0].size) {
                if (board[i][j] == 0) {
                    continue
                }
                // check vertically, focus on top point
                if (i + 2 < board.size
                        && abs(board[i][j]) == abs(board[i + 1][j])
                        && abs(board[i + 2][j]) == abs(board[i][j])
                        && (i == 0 || abs(board[i][j]) != abs(board[i - 1][j]))) {
                    hasCrush = true
                    var k = 0
                    while (i + k < board.size && abs(board[i][j]) == abs(board[i + k][j])) {
                        board[i + k][j] = -abs(board[i][j])
                        k++
                    }
                }
                // check horizontally, focus on left point
                if (j + 2 < board[0].size
                        && abs(board[i][j]) == abs(board[i][j + 1]) && abs(board[i][j]) == abs(board[i][j + 2])
                        && (j == 0 || abs(board[i][j]) != abs(board[i][j - 1]))) {
                    hasCrush = true
                    var k = 0
                    while (j + k < board[0].size && abs(board[i][j]) == abs(board[i][j + k])) {
                        board[i][j + k] = -abs(board[i][j])
                        k++
                    }
                }
            }
        }
        return hasCrush
    }

    // T:O(m*n) S:O(1)
    private fun performFall(board: Array<IntArray>) {
        for (j in 0 until board[0].size) {
            var i = board.size - 1
            for (r in (board.size - 1) downTo 0) {
                if (board[r][j] > 0) {
                    board[i][j] = board[r][j]
                    i--
                }
            }
            for (k in 0 until i + 1) {
                board[k][j] = 0
            }
        }
    }
}


fun main(args: Array<String>) {
    val s = Solution_candy_crush()
    for (arr in s.getStableBoard2(arrayOf(
            intArrayOf(110, 5, 112, 113, 114),
            intArrayOf(210, 211, 5, 213, 214),
            intArrayOf(310, 311, 3, 313, 314),
            intArrayOf(410, 411, 412, 5, 414),
            intArrayOf(5, 1, 512, 3, 3),
            intArrayOf(610, 4, 1, 613, 614),
            intArrayOf(710, 1, 2, 713, 714),
            intArrayOf(810, 1, 2, 1, 1),
            intArrayOf(1, 1, 2, 2, 2),
            intArrayOf(4, 1, 4, 4, 1014)
    ))) {
        println(arr.toList())
    }
    for (arr in s.getStableBoard(arrayOf(
            intArrayOf(110, 5, 112, 113, 114),
            intArrayOf(210, 211, 5, 213, 214),
            intArrayOf(310, 311, 3, 313, 314),
            intArrayOf(410, 411, 412, 5, 414),
            intArrayOf(5, 1, 512, 3, 3),
            intArrayOf(610, 4, 1, 613, 614),
            intArrayOf(710, 1, 2, 713, 714),
            intArrayOf(810, 1, 2, 1, 1),
            intArrayOf(1, 1, 2, 2, 2),
            intArrayOf(4, 1, 4, 4, 1014)
    ))) {
        println(arr.toList())
    }
}