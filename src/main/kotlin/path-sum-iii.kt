/**
 * Given the root of a binary tree and an integer targetSum,
 * return the number of paths where the sum of the values along the path equals targetSum.
 * The path does not need to start or end at the root or a leaf,
 * but it must go downwards (i.e., traveling only from parent nodes to child nodes).
 */
class Solution_path_sum_iii {

    // Using prefix sum, T:O(n) S:O(n)
    fun pathSum(root: TreeNode?, targetSum: Int): Int {
        val prefixSum = hashMapOf(0L to 1)
        return helper(root, targetSum, prefixSum, 0)
    }

    private fun helper(n: TreeNode?, t: Int, prefixSum: HashMap<Long, Int>, curSum: Long): Int {
        if (n == null) return 0
        val newSum: Long = curSum + n.`val`
        var ret = prefixSum.getOrDefault(newSum - t, 0)
        prefixSum.putIfAbsent(newSum, 0)
        prefixSum[newSum] = prefixSum[newSum]!! + 1
        ret += helper(n.left, t, prefixSum, newSum) + helper(n.right, t, prefixSum, newSum)
        // Since prefix sum is only available for one path, need back tracking,
        // otherwise right prefix sum will be affected by left prefix sum
        prefixSum[newSum] = prefixSum[newSum]!! - 1
        return ret
    }

    private var ret = 0

    // AC solution using recursion. T:O(n^2) since left sum and right sum are most size n and we iterate through the tree
    // S:O(n)
    fun pathSumRecur(root: TreeNode?, targetSum: Int): Int {
        if (root == null) return 0
        ret = 0
        postOrder(root, targetSum)
        return ret
    }

    private fun postOrder(n: TreeNode, targetSum: Int): List<Long> {
        val leftSum = if (n.left != null) postOrder(n.left!!, targetSum) else emptyList()
        val rightSum = if (n.right != null) postOrder(n.right!!, targetSum) else emptyList()

        val list = mutableListOf<Long>()
        if (n.`val` == targetSum) {
            ret++
        }
        list.add(n.`val`.toLong())
        for (e in leftSum) {
            val s = e + n.`val`
            if (s == targetSum.toLong()) {
                ret++
            }
            list.add(s)
        }
        for (e in rightSum) {
            val s = e + n.`val`
            if (s == targetSum.toLong()) {
                ret++
            }
            list.add(s)
        }
        return list
    }
}