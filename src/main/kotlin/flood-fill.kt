/**
 * An image is represented by an m x n integer grid image where image[i][j] represents the pixel value of the image.
 * You are also given three integers sr, sc, and color.
 * You should perform a flood fill on the image starting from the pixel image[sr][sc].
 * To perform a flood fill, consider the starting pixel, plus any pixels connected 4-directionally to
 * the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels
 * (also with the same color), and so on. Replace the color of all of the aforementioned pixels with color.
 * Return the modified image after performing the flood fill.
 */
class Solution_flood_fill {

    private val dir = listOf(listOf(1, 0), listOf(0, 1), listOf(0, -1), listOf(-1, 0))

    // Classic BFS. T:O(m*n) S:O(m*n)
    fun floodFill(image: Array<IntArray>, sr: Int, sc: Int, color: Int): Array<IntArray> {
        val prev = image[sr][sc]
        if (prev == color) return image

        val m = image.size
        val n = image[0].size
        val q = LinkedList<IntArray>()
        q.offer(intArrayOf(sr, sc))
        while (q.isNotEmpty()) {
            val cur = q.poll()
            val r = cur[0]
            val c = cur[1]
            image[r][c] = color
            for (d in dir) {
                val nr = r + d[0]
                val nc = c + d[1]
                if (nr in 0 until m && nc in 0 until n && image[nr][nc] == prev) {
                    image[nr][nc] = color
                    q.offer(intArrayOf(nr, nc))
                }
            }
        }
        return image
    }
}