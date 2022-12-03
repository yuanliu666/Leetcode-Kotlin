/**
 * Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents a different task.
 * Tasks could be done in any order.
 * Each task is done in one unit of time.
 * For each unit of time, the CPU could complete either one task or just be idle.
 * However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array),
 * that is that there must be at least n units of time between any two same tasks.
 * Return the least number of units of times that the CPU will take to finish all the given tasks.
 *
 * 1 <= task.length <= 10^4
 * tasks[i] is upper-case English letter.
 * The integer n is in the range [0, 100].
 */
class Solution_task_scheduler {

    // Math solution T:O(N) S:O(1)
    fun leastInterval(tasks: CharArray, n: Int): Int {
        val counter = IntArray(26)
        var max = 0
        var maxCount = 0
        for (task in tasks) {
            counter[task - 'A']++
            if (max == counter[task - 'A']) {
                maxCount++
            } else if (max < counter[task - 'A']) {
                max = counter[task - 'A']
                maxCount = 1
            }
        }
        // Fact: result = tasks + idles
        // Think about case of 6A,6B,4C,4D with n = 2
        // max = 6, maxCount = 2, we want to have at least 6 cycles with L>=3 to hold those max chars
        // [AB_]x6
        // But since their count is max, the last one is not required to be filled
        // Hence, partCount = max - 1
        // And the length to be filled for each cycle is (n+1-maxCount)
        // Which will give partCount * partLength empty slots
        // Then we look at how many chars left besides max chars: availableTasks = tasks.size - max * maxCount
        // If we can fit them in the slots, there will be some idles left, since those slots are required for cool down
        // Otherwise, we filled all the slots, maybe some tasks left, but there won't be conflicts, why?
        // Because we can extend the cycle as needed. Since rest won't have max count, they can be addressed in max - 1 cycles.
        // e.g. one solution is [ABCDABCDABCDABCABDAB]
        val partCount = max - 1
        val partLength = n + 1 - maxCount
        val emptySlots = partCount * partLength
        val availableTasks = tasks.size - max * maxCount
        val idles = kotlin.math.max(0, emptySlots - availableTasks)

        return tasks.size + idles
    }

    // AC solution with simulation. Get max count chars to form cycles of length (n+1)
    // Because it is limited to 26 chars, can think this as T:O(N) S:O(1)
    fun leastInterval2(tasks: CharArray, n: Int): Int {
        if (n == 0) return tasks.size

        val cnt = IntArray(26)
        for (c in tasks) {
            cnt[c - 'A']++
        }

        // Max heap
        val heap = PriorityQueue<Pair<Char, Int>>(compareBy { -it.second })
        for (i in cnt.indices) {
            if (cnt[i] > 0) heap.offer(Pair('A' + i, cnt[i]))
        }
        var ret = 0
        while (heap.isNotEmpty()) {
            var t = 0
            val temp = mutableListOf<Pair<Char, Int>>()
            // Try form cycle of length n + 1 with max count chars
            for (i in 0..n) {
                if (heap.isEmpty()) break
                val pair = heap.poll()
                t++
                if (pair.second > 1) {
                    temp.add(Pair(pair.first, pair.second - 1))
                }
            }
            for (p in temp) heap.offer(p)
            // No idle needed for last one
            ret += if (heap.isEmpty()) t else kotlin.math.max(t, n + 1)
        }
        return ret
    }
}