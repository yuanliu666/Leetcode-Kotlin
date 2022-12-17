class Solution_number_of_provinces {

    class UnionFind(n: Int) {
        // p stands for parents
        private val p: IntArray = IntArray(n)
        private var cnt = 0

        init {
            for (i in 0 until n) {
                p[i] = i
            }
            cnt = n
        }

        // T:O(logn) S:O(1)
        fun find(i: Int): Int {
            var i = i
            while (p[i] != i) {
                p[i] = p[p[i]]
                i = p[i]
            }
            return i
        }

        // T:O(logn) S:O(1)
        fun union(i: Int, j: Int) {
            val a = find(i)
            val b = find(j)
            if (a != b) {
                p[a] = b
                cnt--
            }
        }

        fun getCount(): Int {
            return cnt
        }
    }

    // Classic union find problem. T:O(n^2*logn), S:O(n)
    fun findCircleNum(isConnected: Array<IntArray>): Int {
        val n = isConnected.size
        val union = UnionFind(n)
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (isConnected[i][j] == 1) {
                    union.union(i, j)
                }
            }
        }
        return union.getCount()
    }
}