/**
 * Given an array nums of distinct integers, return all the possible permutations.
 * You can return the answer in any order.
 */
class Solution_permutations {

    // T:O(n*n!) S:O(n)
    fun permute(nums: IntArray): List<List<Int>> {
        val ret = mutableListOf<List<Int>>()
        helper(nums, mutableListOf(), ret, BooleanArray(nums.size))
        return ret
    }

    private fun helper(n: IntArray, path: MutableList<Int>, ret: MutableList<List<Int>>, used: BooleanArray) {
        if (path.size == n.size) {
            ret.add(ArrayList(path))
        } else {
            for (i in n.indices) {
                if (!used[i]) {
                    path.add(n[i])
                    used[i] = true
                    helper(n, path, ret, used)
                    path.remove(n[i])
                    used[i] = false
                }
            }
        }
    }
}