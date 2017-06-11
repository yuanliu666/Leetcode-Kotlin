/**
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
 * The distance between two adjacent cells is 1.
 *
 * Example 1:
 *
 * Input:
 * 0 0 0
 * 0 1 0
 * 0 0 0
 *
 * Output:
 * 0 0 0
 * 0 1 0
 * 0 0 0
 *
 * Example 2:
 *
 * Input:
 * 0 0 0
 * 0 1 0
 * 1 1 1
 *
 * Output:
 * 0 0 0
 * 0 1 0
 * 1 2 1
 *
 * Note:
 * The number of elements of the given matrix will not exceed 10,000.
 * There are at least one 0 in the given matrix.
 * The cells are adjacent in only four directions: up, down, left and right.
 */
import utils.myDeque

class Solution {
    // T:O(m*n) S:O(m*n)
    fun updateMatrix(m: Array<Array<Int>>): Array<Array<Int>> {

        // use deque to get init 0s
        val deque = myDeque<Pair<Int, Int>>()
        for (i in 0..(m.size - 1))
            for (j in 0..(m[0].size - 1))
                when (m[i][j]) {
                    0 -> deque.addFirst(Pair(i, j))
                    else -> m[i][j] = Int.MAX_VALUE
                }


        // BFS
        val directions: Array<Pair<Int, Int>> = arrayOf(Pair(1, 0), Pair(0, 1), Pair(-1, 0), Pair(0, -1))
        while (deque.peekFirst() != null) {
            val element: Pair<Int, Int> = deque.getFirst() as Pair<Int, Int>
            for ((k, h) in directions) {
                val neighbor_i: Int = element.first + k
                val neighbor_j: Int = element.second + h
                val isNeighborValid = 0 <= neighbor_i && neighbor_i < m.size && 0 <= neighbor_j && neighbor_j < m[0].size
                when {
                    (isNeighborValid && m[neighbor_i][neighbor_j] > m[element.first][element.second] + 1)
                    -> {
                        deque.addLast(Pair(neighbor_i, neighbor_j))
                        m[neighbor_i][neighbor_j] = m[element.first][element.second] + 1
                    }
                }
            }
        }

        return m
    }
}

fun main(args: Array<String>) {
    val s = Solution()
    val a = arrayOf(
            arrayOf(1, 0, 0, 0),
            arrayOf(1, 1, 0, 0),
            arrayOf(1, 1, 1, 0))
    for (arr in s.updateMatrix(a)) {
        for (i in arr) {
            print(i)
            print(" ")
        }
        println()
    }
}
