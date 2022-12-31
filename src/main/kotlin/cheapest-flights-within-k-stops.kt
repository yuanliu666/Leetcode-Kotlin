/**
 * There are n cities connected by m flights.
 * Each fight starts from city u and arrives at v with a price w.
 *
 * Now given all the cities and fights, together with starting city src and the destination dst,
 * your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.
 *
 * Example 1:
 * Input:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 1
 * Output: 200
 * Explanation:
 * The graph looks like this: (https://s3-lc-upload.s3.amazonaws.com/uploads/2018/02/16/995.png)
 *
 * The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
 *
 * Example 2:
 * Input:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 0
 * Output: 500
 * Explanation:
 * The graph looks like this: (https://s3-lc-upload.s3.amazonaws.com/uploads/2018/02/16/995.png)
 *
 * The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
 *
 * Note:
 *
 * 1.The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
 * 2.The size of flights will be in range [0, n * (n - 1) / 2].
 * 3.The format of each flight will be (src, dst, price).
 * 4.The price of each flight will be in the range [1, 10000].
 * 5.k is in the range of [0, n - 1].
 * 6.There will not be any duplicated flights or self cycles.
 */

import java.lang.Integer.min


class Solution_cheapest_flights_within_k_stops {

    // TLE now
    // fun fact: parameter n actually has no use here

    // BFS solution
    // each time we go further 1 stop until k stops and get the cheapest or none
    // because the problem promises there is no self cycles, we can save a visited lookup table

    // T:O(|E|*|V|) S:O(|E| + |V|)
    fun findCheapestPrice(@Suppress("UNUSED_PARAMETER") n: Int, flights: Array<IntArray>, src: Int, dst: Int, K: Int): Int {
        // put flights into a map for lookup, as src~listOf((dst, price)) format
        val map = HashMap<Int, MutableList<Pair<Int, Int>>>()
        for (arr in flights) {
            map.putIfAbsent(arr[0], mutableListOf())
            map[arr[0]]?.add(Pair(arr[1], arr[2]))
        }

        var ret = 0xffffff
        // use list of (city, total price) to record current trip
        var trips = mutableListOf(Pair(src, 0))
        var cnt = 0
        var next: MutableList<Pair<Int, Int>>
        while (trips.isNotEmpty() && cnt < K + 1) {
            next = mutableListOf()
            for (trip in trips) {
                if (trip.second >= ret) {
                    // cut branch, otherwise TLE
                    continue
                }
                map[trip.first]?.let {
                    for ((d, p) in it) {
                        // (dst, price)
                        if (d == dst) {
                            ret = min(ret, trip.second + p)
                            // no need to add to next
                        } else {
                            next.add(Pair(d, trip.second + p))
                        }
                    }

                }
            }
            trips = next
            cnt++
        }
        return if (ret == 0xffffff) {
            -1
        } else {
            ret
        }
    }

    // TLE now
    // min heap solution
    // instead of going 1 step from each trip as BFS, we go 1 step from cheapest trip so far
    // and once the cheapest flight from the heap is to dst, that is answer
    // (obviously this is THE cheapest flight to dst if any, and rest trips' total prices will be getting even larger)

    // T:O(|E| + |V|log|V|) S:O(|E| + |V|)
    fun findCheapestPrice2(@Suppress("UNUSED_PARAMETER") n: Int, flights: Array<IntArray>, src: Int, dst: Int, K: Int): Int {
        // put flights into a map for lookup, as src~listOf((dst, price)) format
        val map = HashMap<Int, MutableList<Pair<Int, Int>>>()
        for (arr in flights) {
            map.putIfAbsent(arr[0], mutableListOf())
            map[arr[0]]?.add(Pair(arr[1], arr[2]))
        }

        // use triple of (dst, total price, stops) to represent trips in min heap
        // key is price
        val pq = java.util.PriorityQueue<Triple<Int, Int, Int>>({ t1, t2 ->
            t1.second.compareTo(t2.second)
        })
        pq.offer(Triple(src, 0, 0))
        while (pq.isNotEmpty()) {
            val t = pq.poll()
            if (t.first == dst) {
                return t.second
            }
            if (t.third > K) {
                continue
            }
            map[t.first]?.let {
                for ((d, p) in it) {
                    pq.offer(Triple(d, p + t.second, t.third + 1))
                }
            }
        }
        return -1
    }

    // Improved min heap solution. To avoid going to visited stops.
    // Why it works? Because we are fetching from price min heap, meaning that if we visited this stop before,
    // it must be a lower cost, hence no point to revisit.
    // Same complexity as min heap solution.
    fun findCheapestPrice3(n: Int, flights: Array<IntArray>, src: Int, dst: Int, k: Int): Int {
        if (src == dst) return 0
        val map = hashMapOf<Int, MutableList<Pair<Int, Int>>>()
        for (f in flights) {
            val from = f[0]
            val to = f[1]
            val price = f[2]
            map.putIfAbsent(from, mutableListOf())
            map[from]?.add(Pair(to, price))
        }

        // Min heap for (total prices, curStop, # stops)
        val heap = PriorityQueue<IntArray>(compareBy { it[0] })
        // Record last visited steps for each stop
        val visited = IntArray(n) { Int.MAX_VALUE }
        heap.offer(intArrayOf(0, src, 0))
        while (heap.isNotEmpty()) {
            val arr = heap.poll()
            val cost = arr[0]
            val curStop = arr[1]
            val steps = arr[2]
            if (curStop == dst) return cost
            if (visited[curStop] < steps || steps > k) continue
            visited[curStop] = steps
            for (p in map[curStop] ?: mutableListOf()) {
                heap.offer(intArrayOf(cost + p.second, p.first, steps + 1))
            }
        }
        return -1
    }
}

fun main(args: Array<String>) {
    // LC OJ passed for both
    val s = Solution_cheapest_flights_within_k_stops()
    println(s.findCheapestPrice(3, arrayOf(intArrayOf(0, 1, 100), intArrayOf(1, 2, 100), intArrayOf(0, 2, 500)), 0, 2, 1))
    println(s.findCheapestPrice2(3, arrayOf(intArrayOf(0, 1, 100), intArrayOf(1, 2, 100), intArrayOf(0, 2, 500)), 0, 2, 1))
    println(s.findCheapestPrice(3, arrayOf(intArrayOf(0, 1, 100), intArrayOf(1, 2, 100), intArrayOf(0, 2, 500)), 0, 2, 0))
    println(s.findCheapestPrice2(3, arrayOf(intArrayOf(0, 1, 100), intArrayOf(1, 2, 100), intArrayOf(0, 2, 500)), 0, 2, 0))
}