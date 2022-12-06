/**
 * Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.
 * In one move, you can increment or decrement an element of the array by 1.
 * Test cases are designed so that the answer will fit in a 32-bit integer.
 */
class Solution_minimum_moves_to_equal_array_elements_ii {

    // Given point k, the moves will be sum of abs(ni - k).
    // Namely, p*k - (n0+n1+...+ni) + (nj+...+n(L-1)) - q*k, where p is number of elements smaller than k,
    // q is the number of elements larger than k.
    // moves = k*(p-q) + [sum of elements greater than k] - [sum of elements smaller than k].
    // At this point we can already solve the problem: just sort the array and track prefix sum,
    // then at a point i we know both p-q and sums, which gives T:O(nlogn) S:O(n).

    // But, the settle point is actually the Median. Why?
    // Take a closer look: we want k*(p-q) to be small
    // and s = [sum of elements greater than k] - [sum of elements smaller than k] to be small, they are kind of going opposite directions:
    // when smaller number increases, k*(p-q) goes up but s goes down.
    // This gives up 2 intuitive points: first is median, where p = q which makes first part smallest;
    // second is after median, which can make s smallest.
    // Let's divide arr into 3 parts, length of L1,L2,L3 and sum of S1,S2,S3,
    // where median is at end of L1, the second point is at end of L2:
    // L1 = L2 + L3, S1 + S2 = S3
    // so move of median = S2+S3-S1=2*S2
    // move of second point = n[L1+L2-1]*(L1+L2-L3)=n[L1+L2-1]*2*L2
    // Because array is ascending, n[L1+L2-1]*L2 >= S2, which means median's move is smaller.
    // It's not a strict proof, but if the second intuitive point is worse, median should be the best choice.
    // T:O(nlogn) S:O(1)
    fun minMoves2(nums: IntArray): Int {
        nums.sort()
        val m: Int = if (nums.size % 2 == 1) nums[nums.size / 2]
        else (nums[nums.size / 2] + nums[nums.size / 2 - 1]) / 2
        var ret = 0
        for (n in nums) {
            ret += kotlin.math.abs(n - m)
        }
        return ret
    }

    // We can also use quick sort with random pivot. Deterministic O(n) but will be O(n^2) in worst case.
    // T:O(n) S:O(1)
    fun minMoves2QuickSelect(nums: IntArray): Int {
        val mid = quickSelect(nums, nums.size / 2)
        return Arrays.stream(nums).map { kotlin.math.abs(it - mid) }.sum()
    }

    private fun quickSelect(arr: IntArray, target: Int): Int {
        var start = 0
        var end = arr.size - 1
        while (start < end) {
            val q = randPartition(arr, start, end)
            if (q == target) return arr[q]
            if (target < q) end = q - 1 else start = q + 1
        }
        return arr[start]
    }

    private fun randPartition(arr: IntArray, p: Int, r: Int): Int {
        if (p >= r) return p
        val index = (Math.random() * (r - p + 1) + p).toInt()
        swap(arr, index, r)
        val pivot = arr[r]
        var i = p - 1
        var j = p
        while (j < r) {
            if (arr[j] <= pivot) swap(arr, ++i, j++) else j++
        }
        swap(arr, ++i, r)
        return i
    }

    private fun swap(arr: IntArray, p: Int, r: Int) {
        val t = arr[p]
        arr[p] = arr[r]
        arr[r] = t
    }
}