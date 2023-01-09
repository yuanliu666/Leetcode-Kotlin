/**
 * You are given a 0-indexed integer array tasks, where tasks[i] represents the difficulty level of a task.
 * In each round, you can complete either 2 or 3 tasks of the same difficulty level.
 * Return the minimum rounds required to complete all the tasks, or -1 if it is not possible to complete all the tasks.
 */
class Solution_minimum_rounds_to_complete_all_tasks {

    // Each task difficulty is essentially independent. And 1 task will fail directly.
    // For n tasks with same difficulty, the idea is try to get as many 3s as possible.
    // T:O(n) S:O(n)
    fun minimumRounds(tasks: IntArray): Int {
        val map = hashMapOf<Int, Int>()
        for (t in tasks) {
            map.putIfAbsent(t, 0)
            map[t] = map[t]!! + 1
        }
        var ret = 0
        for (v in map.values) {
            if (v == 1) return -1
            ret += getRounds(v)
        }
        return ret
    }

    private fun getRounds(n: Int): Int {
        return when (n % 3) {
            0 -> n / 3
            2 -> n / 3 + 1
            else -> {   // 1
                (n - 3) / 3 + 2
            }
        }
    }
}