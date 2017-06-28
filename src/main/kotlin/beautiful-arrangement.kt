/**
 * Suppose you have N integers from 1 to N.
 * We define a beautiful arrangement as an array that is constructed by these N numbers successfully
 * if one of the following is true for the ith position (1 <= i <= N) in this array:
 *
 * The number at the ith position is divisible by i.
 * i is divisible by the number at the ith position.
 * Now given N, how many beautiful arrangements can you construct?
 *
 * Example 1:
 * Input: 2
 * Output: 2
 * Explanation:
 *
 * The first beautiful arrangement is [1, 2]:
 *
 * Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).
 *
 * Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).
 *
 * The second beautiful arrangement is [2, 1]:
 *
 * Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).
 *
 * Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.
 * Note:
 * N is a positive integer and will not exceed 15.
 */

// The list will always contain all distinct numbers from 1 to n. So it's just a matter of arrangement.
// We start from end, trying swap to fit for this position. And if everything goes well, we finish all elements and
// we can add 1 to count.
// Two things to notice in following code:
// 1. for (i in 0..n - 1), i can be n - 1, so no swap is also included
// 2. Swap does not mean it's valid for now. For example, 3 at position 6 can swap with 9 at position 9,
// but this is not valid. Of course 9 will be swapped afterwards since it does not fit 6.

// T:O(n!) S:O(n)
class Solution_beautiful_arrangement {
    fun getCnt(n: Int): Int {
        return helper(n, (1..n).distinct().toMutableList())
    }

    fun helper(n: Int, arr: MutableList<Int>): Int {
        if (n == 0)
            return 1
        var ret = 0
        for (i in 0..n - 1) {
            if (arr[i] % n == 0 || n % arr[i] == 0) {
                var temp = arr[i]
                arr[i] = arr[n - 1]
                arr[n - 1] = temp
                ret += helper(n - 1, arr)
                temp = arr[i]
                arr[i] = arr[arr.size - 1]
                arr[arr.size - 1] = temp
            }
        }
        return ret
    }
}

fun main(args: Array<String>) {
    val s = Solution_beautiful_arrangement()
    println(s.getCnt(2))
    println(s.getCnt(3))
    println(s.getCnt(4))
}