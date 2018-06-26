/**
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 *
 * Example:
 *
 * Input: n = 4, k = 2
 * Output:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 */

class Solution_combinations {

    // T: O(k * C(n, k))
    // S: O(k)

    // DFS solution
    fun combine(n: Int, k: Int): List<List<Int>> {
        val ret = mutableListOf<List<Int>>()
        helper(mutableListOf(), n, k, ret)
        return ret
    }

    private fun helper(temp: MutableList<Int>, n: Int, k: Int, ret: MutableList<List<Int>>) {
        if (k == 0) {
            ret.add(temp.toList())
            return
        } else {
            val start = if (temp.isEmpty()) {
                1
            } else {
                temp.last() + 1
            }
            for (i in start..n) {
                temp.add(i)
                helper(temp, n, k - 1, ret)
                temp.remove(i)
            }
        }
    }

    // create an init combination list and change it step by step
    fun combine2(n: Int, k: Int): List<List<Int>> {
        val cache = IntArray(k, { it + 1 })
        val result = mutableListOf<List<Int>>()
        out@ while (true) {
            result.add(cache.toList())
            for (index in (0 until k).reversed()) {
                if (cache[index] > n + index - k) {
                    // cache[k-1] > n - 1
                    // cache[0] > n - k
                    continue
                } else {
                    cache[index] += 1
                    for (next in (index + 1) until k) {
                        cache[next] = cache[next - 1] + 1
                    }
                    continue@out
                }
            }
            break
        }
        return result
    }
}

fun main(args: Array<String>) {
    // LC OJ passed
    // see [CombinationsTest] for unit test
}