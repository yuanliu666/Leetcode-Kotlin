/**
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 */
class Solution_subsets_ii {

    // Recursion. T:O(n*2^n) S:O(n)
    fun subsetsWithDup(nums: IntArray): List<List<Int>> {
        nums.sort()
        val ret = mutableListOf<List<Int>>()
        helper(nums, 0, mutableListOf(), ret)
        return ret
    }

    private fun helper(nums: IntArray, i: Int, path: MutableList<Int>, ret: MutableList<List<Int>>) {
        if (i >= nums.size) {
            ret.add(ArrayList(path))
            return
        }
        var j = i + 1
        while (j < nums.size && nums[i] == nums[j]) {
            j++
        }
        val cnt = j - i
        // Not adding any
        helper(nums, j, path, ret)
        // Recur with different count
        for (k in 0 until cnt) {
            path.add(nums[i])
            helper(nums, j, path, ret)
        }
        // backtracking
        for (k in 0 until cnt) {
            path.remove(nums[i])
        }
    }

    // Another flavor. Still skip duplicates and shorter.
    private fun helper2(nums: IntArray, i: Int, path: MutableList<Int>, ret: MutableList<List<Int>>) {
        ret.add(ArrayList(path))
        for (ind in i until nums.size) {
            if (ind != i && nums[ind] == nums[ind - 1]) continue
            path.add(nums[ind])
            helper2(nums, ind + 1, path, ret)
            path.removeAt(path.size - 1)
        }
    }

    // Iterative solution. Time complexity is the same, S:O(logn) from sort function.
    // The idea is, for duplicates, only append to last round of results
    // e.g. 1,2,2
    // 1. start with empty list [], ret = [[]]
    // 2. got 1, add [1], ret = [[], [1]]
    // 3. got 2, append to each of ret and add back, adding [2] and [1,2], ret = [[], [1], [2], [1,2]]
    // 4. got another 2. If we still do 3, we will get duplicates like [2]. Instead, appending on 3's result,
    // that is [2,2], [1,2,2], this way it contains different number of 2s and won't be duplicate, and
    // ret = [[], [1], [2], [1,2], [2,2], [1,2,2]]
    fun subsetsWithDupIterative(nums: IntArray): List<List<Int>> {
        Arrays.sort(nums)
        val subsets: MutableList<List<Int>> = mutableListOf()
        subsets.add(mutableListOf())
        var subsetSize = 0
        for (i in nums.indices) {
            val startingIndex = if (i >= 1 && nums[i] == nums[i - 1]) subsetSize else 0
            // subsetSize refers to the size of the subset in the previous step. This value also indicates the starting index of the subsets generated in this step.
            subsetSize = subsets.size
            for (j in startingIndex until subsetSize) {
                val currentSubset: MutableList<Int> = ArrayList(subsets[j])
                currentSubset.add(nums[i])
                subsets.add(currentSubset)
            }
        }
        return subsets
    }

    // Another idea is to use bit mask, basically treat each index as a digit place and iterate through all possible numbers.
    // The downside is need to remove duplicate with a set, which will be S:O(n*2^n)
}