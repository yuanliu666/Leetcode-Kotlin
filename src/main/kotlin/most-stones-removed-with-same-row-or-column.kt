/**
 * On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.
 * A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.
 * Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone,
 * return the largest possible number of stones that can be removed.
 */
class Solution_most_stones_removed_with_same_row_or_column {

    class UnionFind(n: Int) {
        private val p = IntArray(n).also {
            for (i in 0 until n) it[i] = i
        }
        private var cnt = n

        fun union(i: Int, j: Int) {
            val a = find(i)
            val b = find(j)
            if (a != b) {
                p[a] = b
                cnt--
            }
        }

        fun find(a: Int): Int {
            var cur = a
            while (p[cur] != cur) {
                p[cur] = p[p[cur]]
                cur = p[cur]
            }
            return cur
        }

        fun getCount() = cnt
    }


    // T:O(nlogn) S:O(n)
    fun removeStones(stones: Array<IntArray>): Int {
        val n = stones.size
        val u = UnionFind(n)
        // axis - last index of stone that has this axis
        val xMap = hashMapOf<Int, Int>()
        val yMap = hashMapOf<Int, Int>()

        for (i in stones.indices) {
            val x = stones[i][0]
            val y = stones[i][1]
            if (xMap.containsKey(x)) {
                u.union(xMap[x]!!, i)
            }
            xMap[x] = i
            if (yMap.containsKey(y)) {
                u.union(yMap[y]!!, i)
            }
            yMap[y] = i
        }

        return n - u.getCount()
    }
}