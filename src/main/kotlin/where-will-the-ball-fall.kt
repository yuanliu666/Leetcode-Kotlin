/**
 * You have a 2-D grid of size m x n representing a box, and you have n balls. The box is open on the top and bottom sides.
 * Each cell in the box has a diagonal board spanning two corners of the cell that can redirect a ball to the right or to the left.
 * A board that redirects the ball to the right spans the top-left corner to the bottom-right corner and is represented in the grid as 1.
 * A board that redirects the ball to the left spans the top-right corner to the bottom-left corner and is represented in the grid as -1.
 * We drop one ball at the top of each column of the box. Each ball can get stuck in the box or fall out of the bottom.
 * A ball gets stuck if it hits a "V" shaped pattern between two boards or if a board redirects the ball into either wall of the box.
 * Return an array answer of size n
 * where answer[i] is the column that the ball falls out of at the bottom after dropping the ball from the ith column at the top,
 * or -1 if the ball gets stuck in the box.
 */
class Solution_where_will_the_ball_fall {

    // T:O(m*n) S:O(n) as recursion stack size is the height
    fun findBall(grid: Array<IntArray>): IntArray {
        val n = grid[0].size
        val ret = IntArray(n)
        for (i in 0 until n) {
            ret[i] = fall(0, i, grid)
        }
        return ret
    }

    // Return the result of ball falling from [x, y]
    private fun fall(x: Int, y: Int, grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size

        if (x >= m) return y

        if (grid[x][y] == -1) {   // '/'
            // Left is wall or '\' will get stuck
            if (y - 1 < 0 || grid[x][y - 1] == 1) return -1
            // Go left
            return fall(x + 1, y - 1, grid)
        } else {    // '\
            // Right is wall or '/' will get stuck
            if (y + 1 >= n || grid[x][y + 1] == -1) return -1
            // Go right
            return fall(x + 1, y + 1, grid)
        }
    }
}