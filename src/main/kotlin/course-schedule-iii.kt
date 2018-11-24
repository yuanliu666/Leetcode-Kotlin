/**
 * There are n different online courses numbered from 1 to n.
 * Each course has some duration(course length) t and closed on dth day.
 * A course should be taken continuously for t days and must be finished before or on the dth day.
 * You will start at the 1st day.
 *
 * Given n online courses represented by pairs (t,d),
 * your task is to find the maximal number of courses that can be taken.
 *
 * Example:
 * Input: [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
 * Output: 3
 * Explanation:
 * There're totally 4 courses, but you can take 3 courses at most:
 * First, take the 1st course, it costs 100 days so you will finish it on the 100th day,
 * and ready to take the next course on the 101st day.
 * Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day,
 * and ready to take the next course on the 1101st day.
 * Third, take the 2nd course, it costs 200 days so you will finish it on the 1300th day.
 * The 4th course cannot be taken now, since you will finish it on the 3300th day, which exceeds the closed date.
 *
 * Note:
 * 1. The integer 1 <= d, t, n <= 10,000.
 * 2. You can't take two courses simultaneously.
 */

class Solution_course_schedule_iii {

    // This problem is not very similar with previous 2 problems, mainly because there is no dependency on courses.

    // An important observations is no reason to leave any blank time.
    // Because courses only have a deadline, there is no limit for start date,
    // so if there is any blank you can always move what is after to close the gap, and that can only be better.
    // This means, courses are continuous, and the duration of courses can be added directly to present current time.

    // Another thought is that we only care about max number of courses taken. So we would prefer courses take less time.
    // Combined with observation above, it is clear that sort courses by deadline can give us a clearer timeline.

    // Now we take courses by sorted deadline order.
    // At one point, it is possible that current time exceeds new course's deadline when we added its duration.
    // This means an adjustment is needed, but how?
    // The greedy way is to kick out the course that takes most time. If the new course is the one, we kick it as well.
    // Because we assume that everything works fine before this new course as induction. Some cases:
    // i) Previously course is the one takes most time.
    // So if we kick it out, and move everything after it to cover the gap it leaves,
    // there will not be any problem since those courses actually are done earlier.
    // Also current time actually becomes smaller and there is no problem to meet new deadline.
    // ii) New course is the biggest one. In this case we just kick it and continue, nothing changes.


    // In this sense, we use a max heap to keep track of the course that takes most time.
    // T:O(nlogn)
    // S:O(k) where k is result pq size
    fun scheduleCourse(courses: Array<IntArray>): Int {
        // sort by deadline
        val sortedCourses: List<IntArray> = courses.sortedBy { it[1] }
        // max heap
        val heap = java.util.PriorityQueue<Int> { o1, o2 -> o2 - o1 }
        // current time
        var now = 0
        for ((t, end) in sortedCourses) {
            now += t
            heap.offer(t)
            if (now > end) {
                now -= heap.poll()
            }
        }
        return heap.size
    }
}

fun main(args: Array<String>) {

}