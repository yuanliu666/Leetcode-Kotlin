/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
 * which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 *
 * Example 1:
 * Input: 2, [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 *
 * Example 2:
 * Input: 2, [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you should
 * also have finished course 1. So it is impossible.
 *
 * Note:
 * 1. The input prerequisites is a graph represented by a list of edges,
 * not adjacency matrices. Read more about how a graph is represented.
 * 2. You may assume that there are no duplicate edges in the input prerequisites.
 */

class Solution_course_schedule {

    // topological sort
    // T:O(n+m) where m is size of prerequisites
    // S:O(m)
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        val inDegreeMap = mutableMapOf<Int, MutableList<Int>>()
        val outDegreeMap = mutableMapOf<Int, MutableList<Int>>()
        // [0, 1] => 0 <- 1
        for (arr in prerequisites) {
            inDegreeMap.putIfAbsent(arr[0], mutableListOf())
            inDegreeMap[arr[0]]?.add(arr[1])
            outDegreeMap.putIfAbsent(arr[1], mutableListOf())
            outDegreeMap[arr[1]]?.add(arr[0])
        }
        // get start edge points, namely those without in degree
        val edgePoints = mutableListOf<Int>()
        for (i in 0 until numCourses) {
            if (!inDegreeMap.containsKey(i)) {
                edgePoints.add(i)
            }
        }
        while (edgePoints.isNotEmpty()) {
            // store next level edge points
            val next = mutableListOf<Int>()
            // remove current edge points and find next
            for (p in edgePoints) {
                outDegreeMap[p]?.forEach {
                    inDegreeMap[it]?.remove(p)
                    if (inDegreeMap[it]?.isEmpty() == true) {
                        inDegreeMap.remove(it)
                        next.add(it)
                    }
                }
            }
            edgePoints.clear()
            edgePoints.addAll(next)
        }
        // check if there is circle
        return inDegreeMap.isEmpty()
    }
}

fun main(args: Array<String>) {

}