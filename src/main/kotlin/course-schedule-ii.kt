/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
 * which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs,
 * return the ordering of courses you should take to finish all courses.
 *
 * There may be multiple correct orders, you just need to return one of them.
 * If it is impossible to finish all courses, return an empty array.
 *
 * Example 1:
 * Input: 2, [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
 * course 0. So the correct course order is [0,1] .
 *
 * Example 2:
 * Input: 4, [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,1,2,3] or [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
 * courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
 *
 * Note:
 * 1. The input prerequisites is a graph represented by a list of edges,
 * not adjacency matrices. Read more about how a graph is represented.
 * 2. You may assume that there are no duplicate edges in the input prerequisites.
 */

class Solution_course_schedule_ii {

    // This is very similar with course schedule i. We just need to record what's been removed during topological sort.
    // T:O(n+m) where m is size of prerequisites
    // S:O(m)
    fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
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
        val edgePoints: MutableList<Int> = (0 until numCourses).filter {
            !inDegreeMap.containsKey(it)
        }.toMutableList()

        val ret = mutableListOf<Int>()
        while (edgePoints.isNotEmpty()) {
            // store next level edge points
            val next = mutableListOf<Int>()
            for (p in edgePoints) {
                // remove current edge points and find next
                outDegreeMap[p]?.forEach {
                    inDegreeMap[it]?.remove(p)
                    if (inDegreeMap[it]?.isEmpty() == true) {
                        inDegreeMap.remove(it)
                        next.add(it)
                    }
                }
                ret.add(p)
            }
            edgePoints.clear()
            edgePoints.addAll(next)
        }
        return if (inDegreeMap.isEmpty()) ret.toIntArray() else intArrayOf()
    }
}

fun main(args: Array<String>) {

}