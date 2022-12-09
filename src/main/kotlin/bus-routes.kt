/**
 * You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.
 * For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
 * You will start at the bus stop source (You are not on any bus initially), and you want to go to the bus stop target.
 * You can travel between bus stops by buses only.
 * Return the least number of buses you must take to travel from source to target.
 * Return -1 if it is not possible.
 */
class Solution_bus_routes {

    // https://leetcode.com/problems/bus-routes/solutions/127510/bus-routes/
    // BFS on buses instead of stops
    // T:O(N^2+sum(blogb)), b is size of a route array, S:O(N^2)
    fun numBusesToDestination(routes: Array<IntArray>, S: Int, T: Int): Int {
        if (S == T) return 0
        val N = routes.size
        val graph: MutableList<MutableList<Int>> = mutableListOf()
        for (i in 0 until N) {
            Arrays.sort(routes[i])
            graph.add(mutableListOf())
        }
        val seen: MutableSet<Int?> = HashSet()
        val targets: MutableSet<Int> = HashSet()
        val queue: Queue<Point> = ArrayDeque()

        // Build the graph.  Two buses are connected if they share at least one bus stop.
        for (i in 0 until N) for (j in i + 1 until N) if (intersect(routes[i], routes[j])) {
            graph[i].add(j)
            graph[j].add(i)
        }

        // Initialize seen, queue, targets.
        // seen represents whether a node has ever been enqueued to queue.
        // queue handles our breadth first search.
        // targets is the set of goal states we have.
        for (i in 0 until N) {
            if (Arrays.binarySearch(routes[i], S) >= 0) {
                seen.add(i)
                queue.offer(Point(i, 0))
            }
            if (Arrays.binarySearch(routes[i], T) >= 0) targets.add(i)
        }
        while (!queue.isEmpty()) {
            val info: Point = queue.poll()
            val node: Int = info.x
            val depth: Int = info.y
            if (targets.contains(node)) return depth + 1
            for (nei in graph[node]) {
                if (!seen.contains(nei)) {
                    seen.add(nei)
                    queue.offer(Point(nei, depth + 1))
                }
            }
        }
        return -1
    }

    // A and B are sorted, therefore T:O(m+n) S:O(1)
    private fun intersect(A: IntArray, B: IntArray): Boolean {
        var i = 0
        var j = 0
        while (i < A.size && j < B.size) {
            if (A[i] == B[j]) return true
            if (A[i] < B[j]) i++ else j++
        }
        return false
    }

    // T:O(N*sum(b^2)) S:O(N^2)
    fun numBusesToDestinationTLE(routes: Array<IntArray>, source: Int, target: Int): Int {
        val map = hashMapOf<Int, MutableSet<Int>>()
        // construct stop - stops map
        for (arr in routes) {
            for (n in arr) {
                map.putIfAbsent(n, mutableSetOf())
                for (m in arr) {
                    if (n == m) continue
                    map[n]?.add(m)
                }
            }
        }
        // bfs
        val list = mutableListOf(source)
        var step = 0
        val visited = hashSetOf(source)
        while (list.isNotEmpty()) {
            val next = mutableListOf<Int>()
            for (stop in list) {
                if (stop == target) return step
                if (map.containsKey(stop)) {
                    for (nextStop in map[stop]!!) {
                        if (!visited.contains(nextStop)) {
                            next.add(nextStop)
                            visited.add(nextStop)
                        }
                    }
                }
            }
            step++
            list.clear()
            list.addAll(next)
        }
        return -1
    }
}