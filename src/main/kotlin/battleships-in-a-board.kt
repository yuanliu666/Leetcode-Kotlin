/**
 * Given an 2D board, count how many different battleships are in it.
 * The battleships are represented with 'X's, empty slots are represented with '.'s.
 * You may assume the following rules:
 *
 * You receive a valid board, made of only battleships or empty slots.
 * Battleships can only be placed horizontally or vertically. In other words,
 * they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column),
 * where N can be of any size.
 * At least one horizontal or vertical cell separates between two battleships -
 * there are no adjacent battleships.
 *
 * Example:
 * X..X
 * ...X
 * ...X
 * In the above board there are 2 battleships.
 * Invalid Example:
 * ...X
 * XXXX
 * ...X
 * This is not a valid board - as battleships will always have a cell separating between them.
 * Your algorithm should not modify the value of the board.
 */

class Solution_battleships_in_a_board {
    // T:O(m*n) S:O(1)
    fun getCnt(b: Array<Array<Char>>): Int {
        var ret = 0
        for (i in 0..b.size - 1)
            for (j in 0..b[0].size - 1) {
                if (b[i][j] == 'X'
                        && (i == b.size - 1 || b[i + 1][j] == '.')
                        && (j == b[0].size - 1 || b[i][j + 1] == '.'))
                    ret++
            }
        return ret
    }
}

fun main(args: Array<String>) {
    val s = Solution_battleships_in_a_board()
    println(s.getCnt(arrayOf(
            arrayOf('X', '.', '.', 'X'),
            arrayOf('.', '.', '.', 'X'),
            arrayOf('.', '.', '.', 'X'))))

    println(s.getCnt(arrayOf(
            arrayOf('X', 'X', '.', 'X'),
            arrayOf('.', '.', '.', 'X'),
            arrayOf('.', '.', '.', 'X'))))

    println(s.getCnt(arrayOf(
            arrayOf('X', '.', '.', 'X'),
            arrayOf('.', 'X', '.', 'X'),
            arrayOf('X', '.', '.', 'X'))))
}
