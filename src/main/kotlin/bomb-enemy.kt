/**
 * Given a 2D grid, each cell is either a wall 'W',
 * an enemy 'E' or empty '0' (the number zero),
 * return the maximum enemies you can kill using one bomb.
 * The bomb kills all the enemies in the same row and column from the planted point
 * until it hits the wall since the wall is too strong to be destroyed.
 * Notice:
 * You can only put the bomb at an empty cell.
 * Example
 * Given a grid:
 * 0 E 0 0
 * E 0 W E
 * 0 E 0 0
 * return 3. (Placing a bomb at (1,1) kills 3 enemies)
 */

class Solution_bomb_enemy {
    // The brute force solution: iterating the matrix, when find a 0, searching 4 directions for E.
    // Apparently brute force is O(m*n*(m+n)), but can we do better?
    // The problem with it is that, do we really need to search?
    // Can we iterate matrix beforehand, and store the info we need?
    // We can have left[i][j], right[i][j], up[i][j], down[i][j] to store the E at 4 directions of 0s.
    // This requires two passes of matrix iteration.

    // T:O(m*n) S:O(m*n)
    fun getMaxBombKill(g: Array<Array<Char>>): Int {
        val left = Array(g.size, { Array(g[0].size, { 0 }) })
        val right = Array(g.size, { Array(g[0].size, { 0 }) })
        val up = Array(g.size, { Array(g[0].size, { 0 }) })
        val down = Array(g.size, { Array(g[0].size, { 0 }) })
        for (i in 0..g.size - 1)
            for (j in 0..g[0].size - 1)
                if (g[i][j] == '0') {
                    // check left
                    if (j - 1 >= 0) {
                        when (g[i][j - 1]) {
                            '0' -> left[i][j] = left[i][j - 1]
                            'E' -> left[i][j] = 1
                        }
                    }
                    // check up
                    if (i - 1 >= 0) {
                        when (g[i - 1][j]) {
                            '0' -> up[i][j] = up[i - 1][j]
                            'E' -> up[i][j] = 1
                        }
                    }
                }
        for (i in (g.size - 1).downTo(0))
            for (j in (g[0].size - 1).downTo(0))
                if (g[i][j] == '0') {
                    // check right
                    if (j + 1 <= g[0].size - 1) {
                        when (g[i][j + 1]) {
                            '0' -> right[i][j] = right[i][j + 1]
                            'E' -> right[i][j] = 1
                        }
                    }
                    // check down
                    if (i + 1 <= g.size - 1) {
                        when (g[i + 1][j]) {
                            '0' -> down[i][j] = down[i + 1][j]
                            'E' -> down[i][j] = 1
                        }
                    }
                }
        var ret = 0
        for (i in 0..g.size - 1)
            for (j in 0..g[0].size - 1)
                if (g[i][j] == '0')
                    ret = maxOf(ret, left[i][j] + right[i][j] + up[i][j] + down[i][j])
        return ret
    }
}

fun main(args: Array<String>) {
    val s = Solution_bomb_enemy()
    println(s.getMaxBombKill(arrayOf(
            arrayOf('0', 'E', '0', '0'),
            arrayOf('E', '0', 'W', 'E'),
            arrayOf('0', 'E', '0', '0')
    )))
}