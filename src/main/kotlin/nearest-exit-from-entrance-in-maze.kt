class Solution_nearest_exit_from_entrance_to_maze {

    private val dir = listOf(listOf(0, 1), listOf(0, -1), listOf(-1, 0), listOf(1, 0))

    // Deal with TLE: mark as visited as soon as put in queue
    // T:O(n) S:O(n)
    fun nearestExit(maze: Array<CharArray>, entrance: IntArray): Int {
        val m = maze.size
        val n = maze[0].size
        val q = LinkedList<IntArray>()
        q.add(entrance)
        var step = 0

        while (q.isNotEmpty()) {
            val next = LinkedList<IntArray>()
            for (cur in q) {
                // Check if it is on border
                if (step > 0 && (cur[0] == 0 || cur[0] == m - 1 || cur[1] == 0 || cur[1] == n - 1)) {
                    return step
                }
                maze[cur[0]][cur[1]] = '+'
                for (d in dir) {
                    val r = cur[0] + d[0]
                    val c = cur[1] + d[1]
                    if (r in 0 until m && c in 0 until n && maze[r][c] != '+') {
                        next.offer(intArrayOf(r, c))
                        maze[r][c] = '+'
                    }
                }
            }
            step++
            q.clear()
            q.addAll(next)
        }
        return -1
    }
}